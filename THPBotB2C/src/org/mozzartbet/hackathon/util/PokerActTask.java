package org.mozzartbet.hackathon.util;

import java.util.Set;
import java.util.concurrent.Callable;

import org.mozzartbet.hackathon.Player;
import org.mozzartbet.hackathon.actions.Action;

public class PokerActTask implements Callable<Action> {

	int bet;
	int minBet;
	Player actor;
	Set<Action> allowedActions;

	public PokerActTask(int bet, int minBet, Player actor, Set<Action> allowedActions) {
		this.bet = bet;
		this.minBet = minBet;
		this.actor = actor;
		this.allowedActions = allowedActions;
	}

	@Override
	public Action call() throws Exception {
		return actor.getBot().act(minBet, bet, allowedActions, actor.getCash());
	}

}
