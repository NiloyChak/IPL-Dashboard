package com.rest.io.ipldashboardbackend.batchprocess;

import com.rest.io.ipldashboardbackend.dto.MatchInput;
import com.rest.io.ipldashboardbackend.model.Match;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MatchProcessor implements ItemProcessor<MatchInput, Match> {
    @Override
    public Match process(final MatchInput matchInput) throws Exception {

        Match match = new Match();
        match.setId(Long.parseLong(matchInput.getId()));
        match.setCity(matchInput.getCity());
        match.setDate(LocalDate.parse(matchInput.getDate()));
        match.setPlayerOfMatch(matchInput.getPlayer_of_match());
        match.setVenue(matchInput.getVenue());

        //Set team1 and team2, where team1 is the toss-winner
        String firstInningsTeam, secondInningsTeam;
        if(matchInput.getToss_decision().equals("bat"))
        {
            firstInningsTeam = matchInput.getToss_winner();
            secondInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1())
                    ? matchInput.getTeam2()
                    : matchInput.getTeam1();
        }
        else
        {
            secondInningsTeam= matchInput.getToss_winner();
            firstInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1())
                    ? matchInput.getTeam2()
                    : matchInput.getTeam1();
        }
        match.setTeam1(firstInningsTeam);
        match.setTeam2(secondInningsTeam);
        match.setTossWinner(matchInput.getToss_winner());
        match.setTossDecision(matchInput.getToss_decision());
        match.setResult(matchInput.getResult());
        match.setResultMargin(matchInput.getResult_margin());
        match.setUmpire1(match.getUmpire1());
        match.setUmpire2(match.getUmpire2());
        return match;
    }
}
