package com.luan.product_manager_api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.luan.product_manager_api.model.Produto;
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    
}