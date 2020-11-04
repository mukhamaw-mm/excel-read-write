package com.mukham.laptopfilereadandwrite.repository;

import com.mukham.laptopfilereadandwrite.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
interface LaptopRep extends JpaRepository<Laptop, Long> {
   List<Laptop>findByDe(boolean b);

   @Query(nativeQuery = true, value = "select * from laptop where laptop_Name=:laptopName and is_deleted=false order by id asc")
  List<Laptop> findByLaptop_Name(@Param("laptopName") String name);

   @Query(nativeQuery = true, value="select *from laptop l where l.laptop_Price<=:price and is_deleted=false order by id asc")
   List<Laptop> getLessThanPrice(@Param("price") Double price);

   @Query(nativeQuery = true, value = "select * from laptop lt where lt.laptop_Price>=:price and is_deleted=false order by id asc")
    List<Laptop> getGreaterThanPrice(@Param("price") Double price);

   @Query(nativeQuery = true, value = "select * from laptop")
    List<Laptop> getLaptopByData();

}
