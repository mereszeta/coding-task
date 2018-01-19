package com.akamai.model;

import java.util.ArrayList;
import java.util.List;

public class TimeUnit {
    private List<TimeUnitJob> jobsAt;

    public TimeUnit() {
        this.jobsAt=new ArrayList<>();
    }

    public List<TimeUnitJob> getJobsAt() {
        return jobsAt;
    }

    public void addJob(TimeUnitJob timeUnitJob){
        this.jobsAt.add(timeUnitJob);
    }

    public int getCurrentCost(){
        if(jobsAt.isEmpty()) return 0;
        else return jobsAt.stream()
                .map(TimeUnitJob::getCost)
                .mapToInt(Integer::intValue).sum();
    }

    @Override
    public String toString() {
        return "TimeUnit{" +
                "jobsAt=" + jobsAt +
                '}';
    }
}
