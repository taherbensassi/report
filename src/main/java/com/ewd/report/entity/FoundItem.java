package com.ewd.report.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/* TODO: 03.11.20 add the user and the category relation */
@Entity
@Table(name="items")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class FoundItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @NotBlank
    private String address;

    private Integer zip;

    private Integer status;

    @NotBlank
    private String city;

    @NotBlank
    private String country;

    private String image;

    private String addressAdditionalInformation;

    @NotBlank
    private String brand;

    private Date dateFound;

    private Time timeFound;

    private String color;

    private String additionalInformation;

    private Integer returned;


    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    public FoundItem() {
    }

    public FoundItem( @NotBlank String name, @NotBlank String address, Integer zip, @NotBlank String city,@NotBlank String country, String addressAdditionalInformation, @NotBlank String brand, Date dateFound, Time timeFound, String color, String additionalInformation) {
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.city = city;
        this.addressAdditionalInformation = addressAdditionalInformation;
        this.brand = brand;
        this.dateFound = dateFound;
        this.timeFound = timeFound;
        this.color = color;
        this.additionalInformation = additionalInformation;
        this.country = country;
    }

    @Override
    public String toString() {
        return "FoundItem{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", zip=" + zip +
                ", city='" + city + '\'' +
                ", addressAdditionalInformation='" + addressAdditionalInformation + '\'' +
                ", brand='" + brand + '\'' +
                ", dateFound=" + dateFound +
                ", timeFound=" + timeFound +
                ", color='" + color + '\'' +
                ", additionalInformation='" + additionalInformation + '\'' +
                ", category=" + category +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getCity() { return city; }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddressAdditionalInformation() {
        return addressAdditionalInformation;
    }

    public void setAddressAdditionalInformation(String addressAdditionalInformation) {
        this.addressAdditionalInformation = addressAdditionalInformation;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Date getDateFound() {
        return dateFound;
    }

    public void setDateFound(Date dateFound) {
        this.dateFound = dateFound;
    }

    public Time getTimeFound() {
        return timeFound;
    }

    public void setTimeFound(Time timeFound) {
        this.timeFound = timeFound;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) { this.additionalInformation = additionalInformation; }

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public Integer getStatus() { return status; }

    public void setStatus(Integer status) { this.status = status; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getReturned() {
        return returned;
    }

    public void setReturned(Integer returned) {
        this.returned = returned;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
