package sit.chains.model;

import sit.chains.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Election {
    public String getVoter() {
        return voter;
    }

    public void setVoter(String voter) {
        this.voter = voter;
    }

    public String getContest() {
        return contest;
    }

    public void setContest(String contest) {
        this.contest = contest;
    }

    @NotNull
    @NotBlank
    private String voter;

    @NotNull
    @NotBlank
    private String contest;

    @Override
    public String toString() {
        return "Election{" +
                "voter='" + voter + '\'' +
                ", contest='" + contest + '\'' +
                '}';
    }
}
