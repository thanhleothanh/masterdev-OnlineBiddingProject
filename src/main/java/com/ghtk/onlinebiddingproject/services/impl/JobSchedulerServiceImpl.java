package com.ghtk.onlinebiddingproject.services.impl;

import com.ghtk.onlinebiddingproject.models.entities.Auction;
import com.ghtk.onlinebiddingproject.services.JobSchedulerService;
import com.ghtk.onlinebiddingproject.utils.JobDetailBuilderUtils;
import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class JobSchedulerServiceImpl implements JobSchedulerService {
    @Autowired
    private Scheduler scheduler;

    @Override
    public void startAuctionScheduler(Auction auction) {
        try {
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("auctionId", auction.getId());

            JobDetail jobDetail = JobDetailBuilderUtils.buildStartAuctionJobDetail(jobDataMap);
            Trigger trigger = JobDetailBuilderUtils.buildJobTrigger(jobDetail, jobDataMap, auction.getTimeStart());

            scheduler.scheduleJob(jobDetail, trigger);

            //deleting scheduled job for auto set auction status to canceled if no admin approve
            boolean deleted = scheduler.deleteJob(new JobKey(JobDetailBuilderUtils.getCancelAuctionJobDetailName(auction.getId()), JobDetailBuilderUtils.getCancelAuctionJobDetailGroup()));
            log.info("deleted scheduled cancellation job: " + deleted);
        } catch (SchedulerException e) {
            log.error(e.getLocalizedMessage());
        }
    }

    @Override
    public void endAuctionScheduler(Auction auction) {
        try {
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("auctionId", auction.getId());

            JobDetail jobDetail = JobDetailBuilderUtils.buildEndAuctionJobDetail(jobDataMap);
            Trigger trigger = JobDetailBuilderUtils.buildJobTrigger(jobDetail, jobDataMap, auction.getTimeEnd());

            //scheduling a job to automatically end the auction when timeEnd comes
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error(e.getLocalizedMessage());
        }

    }

    @Override
    public void cancelAuctionScheduler(Auction auction) {
        try {
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("auctionId", auction.getId());

            JobDetail jobDetail = JobDetailBuilderUtils.buildCancelAuctionJobDetail(jobDataMap);
            Trigger trigger = JobDetailBuilderUtils.buildJobTrigger(jobDetail, jobDataMap, auction.getTimeStart());

            //scheduling a job to automatically cancel the auction if no admin approves
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error(e.getLocalizedMessage());
        }
    }

}
