package com.qasmi.market.importproductbatchservice.domain;

import static com.qasmi.market.importproductbatchservice.constants.JobConstants.*;

import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.qasmi.market.importproductbatchservice.base.AbstractMongoBatchEntity;

/**
 * {@link Product} implements pojo for product.
 * 
 * @author Muhammad Ali Qasmi
 * @since 1.0.0
 */
@Document(collection = PRDOUCT_COLLECTION_NAME)
@XmlRootElement(name = XML_ROOT_ELEMENT)
public class Product extends AbstractMongoBatchEntity{
    
    @NotBlank(message = "Category is required.")
    private String category;
    
    @NotBlank(message = "Name is required.")
    private String name;
    
    private String description;
    
    /**
     * No argument default constuctor.
     */
    public Product() { }
    
    /**
     * Creates instance of {@link Product}. 
     * Requires product's category and name. 
     * 
     * @param category Category of the product.
     * @param name Name of the product.
     */
    @JsonCreator
    public Product(@JsonProperty("category") final String category, //
            @JsonProperty("name") final String name) {
        this.category = category;
        this.name = name;
    }
    
    /**
     * @return the category
     */
    @XmlElement(name = "category")
    public String getCategory() {
        return category;
    }
    
    /**
     * @param category the category to set
     */
    public void setCategory(final String category) {
        this.category = category;
    }

    /**
     * @return the name
     */
    @XmlElement(name = "name")
    public String getName() {
        return name;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    @XmlElement(name = "description")
    public String getDescription() {
        return description;
    }
    
    /**
     * @param description the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!(object instanceof Product)) {
            return false;
        }
        final Product rhsObject = (Product) object;
        return new EqualsBuilder() //
                .append(getCategory(), rhsObject.getCategory()) //
                .append(getName(), rhsObject.getName()) //
                .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
        return new HashCodeBuilder() //
                .append(getCategory())
                .append(getName()) //
                .hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this) //
                .append("Category", getCategory()) //
                .append("Name", getName()) //
                .append("Description", getDescription()) //
                .toString();
    }

}
