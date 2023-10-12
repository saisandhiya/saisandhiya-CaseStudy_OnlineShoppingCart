package com.naidu.payment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.naidu.payment.entity.Payment;

public interface PaymentRepository extends MongoRepository<Payment, Integer> {

	Payment findByTransactionId(int transactionId);

	boolean existsByTransactionId(int transactionId);

}
