package org.simmer.knu_scraping.redis;

import org.springframework.data.repository.CrudRepository;

public interface RedisCRUDRepository extends CrudRepository<RecentNotice, Integer>{
}
