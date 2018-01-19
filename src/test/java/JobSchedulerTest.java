import com.akamai.logic.JobScheduler;
import com.akamai.model.Job;
import com.akamai.model.JobList;
import com.akamai.model.JobSchedule;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.util.ArrayList;
import java.util.List;

public class JobSchedulerTest {
    @Test
    public void jobSchedulerTest_EmptyJobList() {
        //given
        JobList jobs = new JobList(new ArrayList<>());
        JobScheduler jobScheduler = new JobScheduler(jobs);

        //when
        JobSchedule jobSchedule = jobScheduler.createSchedule();

        //then
        Assert.assertEquals(jobSchedule.getTimeUnitsCount(), 1);
    }

    @DataProvider
    private Object[][] validListOfJobs() {
        return new Object[][]{
                {Lists.newArrayList(
                        new Job(0, 2, 1, 1),
                        new Job(1, 3, 2, 1),
                        new Job(2, 4, 1, 1),
                        new Job(3, 3, 1, 1)
                )}
        };
    }

    @Test(dataProvider = "validListOfJobs")
    public void jobSchedulerTest_ValidJobList(List<Job> jobs) {
        //given
        JobList jobList = new JobList(jobs);
        JobScheduler jobScheduler = new JobScheduler(jobList);

        //when
        JobSchedule jobSchedule = jobScheduler.createSchedule();

        //then
        Assert.assertNotEquals(jobSchedule.getTimeUnitsCount(), 1);
    }
}
