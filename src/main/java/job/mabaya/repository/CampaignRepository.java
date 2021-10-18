package job.mabaya.repository;

import job.mabaya.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, String> {
    List<Campaign> findByStartDateGreaterThan(Date startDate);
}
