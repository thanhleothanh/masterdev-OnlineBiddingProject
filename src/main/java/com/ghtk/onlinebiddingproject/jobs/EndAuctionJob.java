package com.ghtk.onlinebiddingproject.jobs;

import com.ghtk.onlinebiddingproject.repositories.AuctionRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EndAuctionJob extends QuartzJobBean {
    @Autowired
    private AuctionRepository auctionRepository;
    @Autowired
    private Scheduler scheduler;

    @Override
    protected void executeInternal(@NotNull JobExecutionContext context) throws JobExecutionException {
        try {
            JobDataMap jobDataMap = context.getMergedJobDataMap();
            Integer auctionId = jobDataMap.getInt("auctionId");
            auctionRepository.endAuction(auctionId);
            auctionRepository.insertWinner(auctionId);
            log.info("ended auction with id " + auctionId);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
    }
}
