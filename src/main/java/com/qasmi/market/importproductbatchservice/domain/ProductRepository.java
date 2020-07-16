package com.qasmi.market.importproductbatchservice.domain;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * {@link ProductRepository} implements {@link MongoRepository} for {@link Product}.
 * 
 * @author Muhammad Ali Qasmi
 * @since 1.0.0
 */
public interface ProductRepository  extends MongoRepository<Product, ObjectId> {

}
