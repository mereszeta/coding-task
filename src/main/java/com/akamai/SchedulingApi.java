package com.akamai;

import com.akamai.logic.JobScheduleService;
import com.akamai.model.TimeUnitJob;
import com.akamai.model.TimeUnitJobForGivenMoment;

import java.io.IOException;
import java.util.List;

public class SchedulingApi {
    private JobScheduleService jobScheduleService;

    public SchedulingApi(String fileName) throws IOException {
        this.jobScheduleService = new JobScheduleService(fileName);
    }

    /**
     * Returns cost of running task in moment specified by timestamp.
     * Note that timestamp should be formatted according to  ISO_LOCAL_DATE_TIME format
     * from DateTimeFormatter and later than moment of using this method
     *
     * @param  timestamp timestamp at which cost will be calculated
     * @return      accumulated cost of all tasks running at specific moment
     */
    public int getInstantCostOfRunningTasksAt(String timestamp) {
        return jobScheduleService.getInstantCostOfRunningTasksAt(timestamp);
    }

    /**
     * Returns list of tasks running at moment specified with parameter timestamp.
     * Note that timestamp should be formatted according to  ISO_LOCAL_DATE_TIME format
     * from DateTimeFormatter and later than moment of using this method
     *
     * @param  timestamp timestamp at which cost will be calculated
     * @return      list of all tasks running at specific moment
     */
    public List<TimeUnitJob> getTasksRunningAt(String timestamp) {
        return jobScheduleService.getTasksRunningAt(timestamp);
    }

    /**
     * Returns first task that will be started after moment specified with parameter timestamp.
     * Note that timestamp should be formatted according to  ISO_LOCAL_DATE_TIME format
     * from DateTimeFormatter and later than moment of using this method
     *
     * @param  timestamp timestamp at which cost will be calculated
     * @return First task to run after given moment
     */

    public TimeUnitJobForGivenMoment getNextRunningTaskWithTime(String timestamp) {
        return jobScheduleService.getNextRunningTaskWithTime(timestamp);
    }
    /**
     * Returns biggest instant cost for specified scheduling plan
     *
     * @return biggest instant cost for specified scheduling plan
     */
    public int getMaximumInstantCost() {
        return jobScheduleService.getMaximumInstantCost();
    }
}