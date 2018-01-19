import com.akamai.SchedulingApi;
import com.akamai.model.TimeUnitJob;
import com.akamai.model.TimeUnitJobForGivenMoment;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SchedulingApiTest {
    private SchedulingApi schedulingApi;

    @BeforeClass
    private void init() {
        try {
            schedulingApi = new SchedulingApi("jobs.csv");
        } catch (IOException ioe) {
            throw new AssertionError();
        }
    }

    @Test
    public void testGettingMaximumInstantCost(){
        //when
        int maxInstantCost = schedulingApi.getMaximumInstantCost();

        //then
        Assert.assertNotEquals(maxInstantCost,0);
    }

    @Test
    public void testGettingTasksRunningAtMoment(){
        //given
        String timestamp = LocalDateTime.now().plusSeconds(7)
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        //when
        List<TimeUnitJob> jobList = schedulingApi.getTasksRunningAt(timestamp);

        //then
        Assert.assertNotNull(jobList);
    }

    @Test
    public void testGettingNextRunningTask(){
        //given
        String timestamp = LocalDateTime.now().plusSeconds(7)
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        //when
        TimeUnitJobForGivenMoment job = schedulingApi.getNextRunningTaskWithTime(timestamp);

        //then
        Assert.assertNotNull(job);
    }

    @Test
    public void testGettingInstantCost(){
        //given
        String timestamp = LocalDateTime.now().plusSeconds(7)
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        int sumOfCostsPlusOne = 12;

        //when
        int instantCost = schedulingApi.getInstantCostOfRunningTasksAt(timestamp);

        //then
        Assert.assertNotEquals(instantCost,sumOfCostsPlusOne);
    }

}
