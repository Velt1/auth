package de.ba.auth.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import de.ba.auth.auth.model.TimeEntry;

@Repository
public interface TimeEntryRepo extends JpaRepository<TimeEntry, Long>{
}