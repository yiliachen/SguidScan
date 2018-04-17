package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.model.BusinessObjects;

/**
 * auto generated at Thu Mar 02 13:24:46 CST 2017 <br />
 * If you use @Query {@link org.springframework.data.jpa.repository.Query} to
 * define the update query you also need to add the @Modifying
 * {@link org.springframework.data.jpa.repository.Modifying} annotation <br />
 * sample query methods show below:
 * 
 * <pre>
 * &#64;Query("select balance from User where username = :username")
 * double getBalanceByUsername(@Param("username") String username);
 * 
 * &#64;Modifying
 * &#64;Query("update User set balance = balance + :balance where username = :username")
 * int increaseBalance(@Param("balance") double balance, @Param("username") String username);
 * 
 * &#64;Query("from User u where u.username = :username")
 * User findUser(@Param("username") String username);
 * </pre>
 */
public interface BusinessObjectsRepository extends JpaRepository<BusinessObjects,Integer>, JpaSpecificationExecutor<BusinessObjects> {

}