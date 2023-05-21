package com.rest.io.ipldashboardbackend.batchprocess;

import com.rest.io.ipldashboardbackend.model.Match;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class JobCompletionNotificationListener implements JobExecutionListener {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println("!!! JOB FINISHED! Time to verify the results");

            jdbcTemplate.query("SELECT player_of_match, team1 FROM match",
                    (rs, row) -> new Match(
                            rs.getString(1),
                            rs.getString(2))
            ).forEach(match -> System.out.println("Found <{{}}> in the database." +match.toString()));
        }
    }
}
