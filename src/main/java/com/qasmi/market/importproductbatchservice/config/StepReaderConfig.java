
package com.qasmi.market.importproductbatchservice.config;

import static com.qasmi.market.importproductbatchservice.constants.JobConstants.*;

import java.io.IOException;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.qasmi.market.importproductbatchservice.domain.Product;

/**
 * This class implements all configuration of step item reader for the batch job.
 * 
 * @author Muhammad Ali Qasmi
 * @since 1.0
 */
@Configuration
public class StepReaderConfig {

    /**
     * Note 'file:' prefix is appended in the path. Furthermore, '*' is used to import all
     * files from the folder.
     */
    private static final String IMPORT_FILES_PATH_FORMAT = "file:%s/*";

    /**
     * Creates instance of product reader for multiple xml files.
     * 
     * @param jobInstanceId Instance id of the job.
     * @param importDirectory Path to the source directory.
     * @param resourceLoader Resource loader instance.
     * @return Multi resource item reader instance.
     * @throws IOException IO Exception instance (if any).
     */
    @Bean
    @StepScope
    public MultiResourceItemReader<Product> mutiFileXMLReaderForProduct(
            @Value("#{jobExecutionContext[jobInstanceId]}") final Long jobInstanceId,
            @Value("${application.import-directory}") final String importDirectory,
            final ResourceLoader resourceLoader) throws IOException {
        final String filePathName = String.format(IMPORT_FILES_PATH_FORMAT,
                importDirectory);
        final Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setClassesToBeBound(Product.class);
        final StaxEventItemReader<Product> staxEventItemReader = new StaxEventItemReader<>();
        staxEventItemReader.setUnmarshaller(jaxb2Marshaller);
        staxEventItemReader.setFragmentRootElementName(XML_ROOT_ELEMENT);
        staxEventItemReader.setResource(resourceLoader.getResource(filePathName));
        final Resource[] resources = ResourcePatternUtils //
                .getResourcePatternResolver(resourceLoader).getResources(filePathName);
        final MultiResourceItemReader<Product> reader = new MultiResourceItemReader<>();
        reader.setResources(resources);
        reader.setDelegate(staxEventItemReader);
        return reader;
    }

}
