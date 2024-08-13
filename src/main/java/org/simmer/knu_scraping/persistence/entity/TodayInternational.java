package org.simmer.knu_scraping.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
public class TodayInternational {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String title;


    public TodayInternational(String title) {
        this.title = title;
    }

    protected TodayInternational() {

    }
}
