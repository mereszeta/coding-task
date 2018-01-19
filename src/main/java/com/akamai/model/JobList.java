package com.akamai.model;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class JobList {
    private List<Job> jobs;

    public JobList(List<Job> jobs) {
        this.jobs = jobs;
    }

    public Integer findLengthOfSingleSequence() {
        return findAllNonDividers()
                .stream()
                .distinct()
                .reduce(1, (a, b) -> a * b);
    }

    private List<Integer> findAllNonDividers() {
        return mapJobsToPeriods()
                .stream()
                .filter(notADivider())
                .collect(toList());
    }

    private Predicate<Integer> notADivider() {
        return candidate ->
                mapJobsToPeriods().stream()
                        .mapToInt(Integer::intValue)
                        .anyMatch(period -> (period != candidate || mapJobsToPeriods().indexOf(period) != mapJobsToPeriods().indexOf(candidate))
                                && period % candidate != 0);
    }

    private List<Integer> mapJobsToPeriods() {
        return jobs.stream()
                .map(Job::getPeriod)
                .collect(toList());
    }

    public boolean isListEmpty() {
        return jobs.isEmpty();
    }

    public Job removeLongestJob() {
        Job job = jobs.stream()
                .max((a, b) ->
                        Comparator.comparingInt(Job::getDuration).compare(a, b))
                .get();
        jobs.remove(job);
        return job;
    }
}
