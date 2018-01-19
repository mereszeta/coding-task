package com.akamai.logic;

import com.akamai.model.Job;
import com.akamai.model.JobList;
import com.akamai.model.JobSchedule;
import com.akamai.model.TimeUnit;

import java.util.ArrayList;
import java.util.List;

import static com.akamai.utils.SchedulerUtils.calculatePossibleStartPositions;
import static com.akamai.utils.SchedulerUtils.getBestStartingPosition;
import static com.akamai.utils.SchedulerUtils.initTable;

public class JobScheduler {
    private JobList jobList;

    public JobScheduler(JobList jobList) {
        this.jobList = jobList;
    }

    public JobSchedule createSchedule() {
        TimeUnit[] timeUnits=new TimeUnit[jobList.findLengthOfSingleSequence()];
        initTable(timeUnits);
        while (!jobList.isListEmpty()) {
            Job job = jobList.removeLongestJob();
            List<Integer> possibleStartPositions = calculatePossibleStartPositions(job);
            Integer chosenPossition = getBestStartingPosition(possibleStartPositions,timeUnits,job.getDuration());
            for (int i = chosenPossition; i < timeUnits.length; i += job.getPeriod()) {
                for (int j = 0; j < job.getDuration(); j++) {
                    timeUnits[i + j].addJob(job.toTimeUnitJob());
                }
            }
        }
        return new JobSchedule(timeUnits);
    }

}
