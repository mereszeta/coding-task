package com.akamai.logic;

import com.akamai.config.JobParser;
import com.akamai.model.JobList;
import com.akamai.model.JobSchedule;
import com.akamai.model.TimeUnitJob;
import com.akamai.model.TimeUnitJobForGivenMoment;
import com.akamai.utils.DateUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JobScheduleService {
    private JobSchedule jobSchedule;
    private DateUtils dateUtils;

    public JobScheduleService(String fileName) throws IOException {
        JobList jobList = new JobList(new JobParser(fileName).parseJobsFromResourceFile());
        this.jobSchedule = new JobScheduler(jobList).createSchedule();
        this.dateUtils = new DateUtils();
    }

    public int getMaximumInstantCost() {
        return this.jobSchedule.getCostsForEveryTimeUnit().stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);
    }

    public int getInstantCostOfRunningTasksAt(String timestamp) {
        int actualPos = getActualPositionByTimestamp(timestamp);
        return jobSchedule.getCostForSpecificTimeUnit(actualPos);
    }

    public List<TimeUnitJob> getTasksRunningAt(String timestamp) {
        int actualPos = getActualPositionByTimestamp(timestamp);
        return jobSchedule.getJobsForSpecificTimeUnit(actualPos);
    }

    public TimeUnitJobForGivenMoment getNextRunningTaskWithTime(String timestamp) {
        int actualPos = getActualPositionByTimestamp(timestamp);
        int circularCounter = actualPos;
        List<TimeUnitJob> currentlyRunning = jobSchedule
                .getJobsForSpecificTimeUnit(actualPos);
        List<TimeUnitJob> nextSec = new ArrayList<>();
        while (currentlyRunning.containsAll(nextSec)){
            if(actualPos<jobSchedule.getTimeUnitsCount()){
                circularCounter++;
                actualPos++;
            }
            else {
                circularCounter=0;
                actualPos++;
            }
            nextSec=jobSchedule.getJobsForSpecificTimeUnit(circularCounter);
        }
        nextSec.removeAll(currentlyRunning);
        String moment = dateUtils.getTimestampByBeginningAndDiff(timestamp,actualPos);
        return new TimeUnitJobForGivenMoment(nextSec.get(0),moment);
    }

    private int getActualPositionByTimestamp(String timestamp) {
        long secondsUntilTimestamp = dateUtils.getSecondDiffFromNow(timestamp);
        return (int) (secondsUntilTimestamp % jobSchedule.getTimeUnitsCount());
    }
}
