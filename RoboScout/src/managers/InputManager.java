package managers;

import components.GraphicButton;

public abstract class InputManager {

	static int defenseScore = 5;
	static int boulderScoreHigh = 5;
	static int boulderScoreLow = 2;

	static int teamScore = 0;

	static int score1 = 0;
	static int score2 = 0;
	static int score3 = 0;
	static int score4 = 0;
	static int score5 = 0;

	static int score_hl = 0;
	static int score_hc = 0;
	static int score_hr = 0;

	static int score_ll = 0;
	static int score_lr = 0;

	public static void mousePressed(GraphicButton c) {
		switch (c.getName()) {
		case "defense_1_plus":
			if (score1 < 9) {
				score1++;
				teamScore += defenseScore;
			}
			ComponentManager.getText().get("defense_1_pts").setDefaultText(Integer.toString(score1));
			ComponentManager.getText().get("team_score").setDefaultText("Team Score-" + teamScore);
			break;
		case "defense_1_minus":
			if (score1 > 0) {
				score1--;
				teamScore -= defenseScore;
			}
			ComponentManager.getText().get("defense_1_pts").setDefaultText(Integer.toString(score1));
			ComponentManager.getText().get("team_score").setDefaultText("Team Score-" + teamScore);
			break;

		case "defense_2_plus":
			if (score2 < 9) {
				score2++;
				teamScore += defenseScore;
			}
			ComponentManager.getText().get("defense_2_pts").setDefaultText(Integer.toString(score2));
			ComponentManager.getText().get("team_score").setDefaultText("Team Score-" + teamScore);
			break;
		case "defense_2_minus":
			if (score2 > 0) {
				score2--;
				teamScore -= defenseScore;

			}
			ComponentManager.getText().get("defense_2_pts").setDefaultText(Integer.toString(score2));
			ComponentManager.getText().get("team_score").setDefaultText("Team Score-" + teamScore);
			break;

		case "defense_3_plus":
			if (score3 < 9) {
				score3++;
				teamScore += defenseScore;
			}
			ComponentManager.getText().get("defense_3_pts").setDefaultText(Integer.toString(score3));
			ComponentManager.getText().get("team_score").setDefaultText("Team Score-" + teamScore);
			break;
		case "defense_3_minus":
			if (score3 > 0) {
				score3--;
				teamScore -= defenseScore;
			}
			ComponentManager.getText().get("defense_3_pts").setDefaultText(Integer.toString(score3));
			ComponentManager.getText().get("team_score").setDefaultText("Team Score-" + teamScore);
			break;

		case "defense_4_plus":
			if (score4 < 9) {
				score4++;
				teamScore += defenseScore;
			}
			ComponentManager.getText().get("defense_4_pts").setDefaultText(Integer.toString(score4));
			ComponentManager.getText().get("team_score").setDefaultText("Team Score-" + teamScore);
			break;
		case "defense_4_minus":
			if (score4 > 0) {
				score4--;
				teamScore -= defenseScore;
			}
			ComponentManager.getText().get("defense_4_pts").setDefaultText(Integer.toString(score4));
			ComponentManager.getText().get("team_score").setDefaultText("Team Score-" + teamScore);
			break;

		case "defense_5_plus":
			if (score5 < 9) {
				score5++;
				teamScore += defenseScore;
			}
			ComponentManager.getText().get("defense_5_pts").setDefaultText(Integer.toString(score5));
			ComponentManager.getText().get("team_score").setDefaultText("Team Score-" + teamScore);
			break;
		case "defense_5_minus":
			if (score5 > 0) {
				score5--;
				teamScore -= defenseScore;
			}
			ComponentManager.getText().get("defense_5_pts").setDefaultText(Integer.toString(score5));
			ComponentManager.getText().get("team_score").setDefaultText("Team Score-" + teamScore);
			break;

		// tower high left

		case "tower_hl_plus":
			if (score_hl < 9) {
				score_hl++;
				teamScore += boulderScoreHigh;
			}
			ComponentManager.getText().get("tower_hl_pts").setDefaultText(Integer.toString(score_hl));
			ComponentManager.getText().get("team_score").setDefaultText("Team Score-" + teamScore);
			break;
		case "tower_hl_minus":
			if (score_hl > 0) {
				score_hl--;
				teamScore -= boulderScoreHigh;
			}
			ComponentManager.getText().get("tower_hl_pts").setDefaultText(Integer.toString(score_hl));
			ComponentManager.getText().get("team_score").setDefaultText("Team Score-" + teamScore);
			break;

		// tower center

		case "tower_hc_plus":
			if (score_hc < 9) {
				score_hc++;
				teamScore += boulderScoreHigh;
			}
			ComponentManager.getText().get("tower_hc_pts").setDefaultText(Integer.toString(score_hc));
			ComponentManager.getText().get("team_score").setDefaultText("Team Score-" + teamScore);
			break;
		case "tower_hc_minus":
			if (score_hc > 0) {
				score_hc--;
				teamScore -= boulderScoreHigh;
			}
			ComponentManager.getText().get("tower_hc_pts").setDefaultText(Integer.toString(score_hc));
			ComponentManager.getText().get("team_score").setDefaultText("Team Score-" + teamScore);
			break;

		// tower high right

		case "tower_hr_plus":
			if (score_hr < 9) {
				score_hr++;
				teamScore += boulderScoreHigh;
			}
			ComponentManager.getText().get("tower_hr_pts").setDefaultText(Integer.toString(score_hr));
			ComponentManager.getText().get("team_score").setDefaultText("Team Score-" + teamScore);
			break;
		case "tower_hr_minus":
			if (score_hr > 0) {
				score_hr--;
				teamScore -= boulderScoreHigh;
			}
			ComponentManager.getText().get("tower_hr_pts").setDefaultText(Integer.toString(score_hr));
			ComponentManager.getText().get("team_score").setDefaultText("Team Score-" + teamScore);
			break;

		// tower low left

		case "tower_ll_plus":
			if (score_ll < 9) {
				score_ll++;
				teamScore += boulderScoreLow;
			}
			ComponentManager.getText().get("tower_ll_pts").setDefaultText(Integer.toString(score_ll));
			ComponentManager.getText().get("team_score").setDefaultText("Team Score-" + teamScore);
			break;
		case "tower_ll_minus":
			if (score_ll > 0) {
				score_ll--;
				teamScore -= boulderScoreLow;
			}
			ComponentManager.getText().get("tower_ll_pts").setDefaultText(Integer.toString(score_ll));
			ComponentManager.getText().get("team_score").setDefaultText("Team Score-" + teamScore);
			break;

		// tower low right

		case "tower_lr_plus":
			if (score_lr < 9) {
				score_lr++;
				teamScore += boulderScoreLow;
			}
			ComponentManager.getText().get("tower_lr_pts").setDefaultText(Integer.toString(score_lr));
			ComponentManager.getText().get("team_score").setDefaultText("Team Score-" + teamScore);
			break;
		case "tower_lr_minus":
			if (score_lr > 0) {
				score_lr--;
				teamScore -= boulderScoreLow;
			}
			ComponentManager.getText().get("tower_lr_pts").setDefaultText(Integer.toString(score_lr));
			ComponentManager.getText().get("team_score").setDefaultText("Team Score-" + teamScore);
			break;
		}

	}

}
