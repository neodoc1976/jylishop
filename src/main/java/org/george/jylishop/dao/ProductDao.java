package org.george.jylishop.dao;

import org.george.jylishop.domain.Hemostatic;
import org.george.jylishop.domain.Manufacturer;
import org.george.jylishop.domain.OpalescenseGel;
import org.george.jylishop.domain.Product;
import org.george.jylishop.exceptoins.ProductIsNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Yulya on 05.11.2016.
 */
@Component
public class ProductDao {
    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public List<Product> getCatalogue() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Product", Product.class).list();
    }

    @Transactional
    public Product getProductById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Product selectedProduct = session.get(Product.class, id);
        if (selectedProduct == null) {
            throw new ProductIsNotFoundException(id);
        }
        return selectedProduct;
    }

    @Transactional
    public void deleteProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(product);
    }

    @Transactional
    private List<Product> getCatalogueOrderByPrice(String sort_type) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Product order by price " + sort_type, Product.class).list();
    }

    @Transactional
    public List<Product> getCatalogueOrderByPriceAsc() {
        return getCatalogueOrderByPrice("ASC");
    }

    @Transactional
    public List<Product> getCatalogueOrderByPriceDesc() {
        return getCatalogueOrderByPrice("DESC");
    }

    @Transactional
    private List<Product> getCatalogueOrderByTitle(String sort_type) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Product order by title " + sort_type, Product.class).list();
    }

    @Transactional
    public List<Product> getCatalogueOrderByTitleByAlphabet() {
        return getCatalogueOrderByTitle("ASC");
    }

    @Transactional
    public List<Product> getCatalogueOrderByTitleReverse() {
        return getCatalogueOrderByTitle("DESC");
    }

    @Transactional
    public List<Product> getCatalogueOrderById() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Product order by id", Product.class).list();
    }

    @Transactional
    public void addProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.save(product);
    }

    @Transactional
    public void updateProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.update(product);
    }

    @Transactional
    private List<Product> getCatalogueOrderByManufacturer(String sort_type) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Product order by manufacturer.name " + sort_type, Product.class).list();
    }

    @Transactional
    public List<Product> getCatalogueOrderByManufacturerByAlphabet() {
        return getCatalogueOrderByManufacturer("ASC");
    }

    @Transactional
    public List<Product> getCatalogueOrderByManufacturerReverse() {
        return getCatalogueOrderByManufacturer("DESC");
    }

    @Transactional
    public List<OpalescenseGel> getOnlyGels() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from OpalescenseGel", OpalescenseGel.class).list();
    }

    @Transactional
    public List<Hemostatic> getOnlyHemos() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Hemostatic", Hemostatic.class).list();
    }

    @Transactional
    public List<Product> getProductListByManufacturer(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("from Product where manufacturer.id = :id", Product.class)
                .setParameter("id", id)
                .list();
    }

    @Transactional
    public void deleteProductListByManufacturer(int id) {
        Session session = sessionFactory.getCurrentSession();
        session
                .createQuery("delete Product where manufacturer.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Transactional
    public long getProductsCountForManufacturer(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (long) session.createQuery("select count (*) from  Product where manufacturer.id=:id")
                .setParameter("id", id)
                .uniqueResult();
    }

    @Transactional
    public void changeManufacturerForProducts(int newId, int oldId) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("update  Product set manufacturer.id=:newId where manufacturer.id=:oldId")
                .setParameter("newId", newId)
                .setParameter("oldId", oldId)
                .executeUpdate();
    }

}
