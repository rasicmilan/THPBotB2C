package org.mozzartbet.hackathon.bots;

import java.util.List;
import java.util.Set;

import org.mozzartbet.hackathon.Card;
import org.mozzartbet.hackathon.Player;
import org.mozzartbet.hackathon.actions.Action;

/**
 * Base class for all Texas Hold'em poker bot implementations.
 */
public interface Bot {

    /**
     * Handles the player joining a table.
     *
     * @param type
     *            The table type (betting structure).
     * @param bigBlind
     *            The table's big blind.
     */
    void joinedTable(int bigBlind);

    /**
     * Handles the start of a new hand.
     *
     * @param dealer
     *            The dealer.
     */
    void handStarted(Player dealer);

    /**
     * Handles the rotation of the actor (the player who's turn it is).
     *
     * @param actor
     *            The new actor.
     */
    void actorRotated(Player actor);

    /**
     * Handles an update of this player.
     *
     * @param player
     *            The player.
     */
    void playerUpdated(Player player);

    /**
     * Handles an update of the board.
     *
     * @param cards
     *            The community cards.
     * @param bet
     *            The current bet.
     * @param pot
     *            The current pot.
     */
    void boardUpdated(List<Card> cards, int bet, int pot);

    /**
     * Handles the event of a player acting.
     *
     * @param player
     *            The player that has acted.
     */
    void playerActed(Player player);

    /**
     * Requests this player to act, selecting one of the allowed actions.
     *
     * @param minBet
     *            The minimum bet.
     * @param currentBet
     *            The current bet.
     * @param allowedActions
     *            The allowed actions.
     *
     * @return The selected action.
     */
    Action act(int minBet, int currentBet, Set<Action> allowedActions, int currentAmount);


    /**
     * name of bot
     * @return
     */
    String getName();


}
