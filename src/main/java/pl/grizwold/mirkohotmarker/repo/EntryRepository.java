package pl.grizwold.mirkohotmarker.repo;

import com.mongodb.WriteResult;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EntryRepository {
    private static final String entryCollection = "entry";

    @Autowired
    private MongoOperations mongoOperations;

    public void markAsHot(Long id, int hour) {
        Query findByIdQuery = new Query();
        findByIdQuery.addCriteria(Criteria.where("_id").is(id));

        Update markAsHot = new Update();
        markAsHot.set("hot" + hour, true);
        markAsHot.set("hot" + hour + "_date", DateTime.now());

        WriteResult writeResult = mongoOperations.updateFirst(findByIdQuery, markAsHot, entryCollection);

        if (writeResult.getN() == 0) {
            log.error("ID: {} is hot but was not found in DB. Tag publicly blacklisted?", id);
        }
    }
}
