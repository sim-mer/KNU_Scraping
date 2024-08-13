package org.simmer.knu_scraping.persistence.repository;

import org.simmer.knu_scraping.persistence.entity.TodayInternational;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodayInternationalJpaRepo extends JpaRepository<TodayInternational, Long>{

}
