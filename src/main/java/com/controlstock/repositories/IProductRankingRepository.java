package com.controlstock.repositories;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.controlstock.entities.ProductRanking;

@Repository("productRankingRepository")

public interface IProductRankingRepository extends JpaRepository<ProductRanking, Serializable> {

	public abstract ProductRanking findById(int id);

}
