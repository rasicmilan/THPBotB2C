package org.mozzartbet.hackathon;

import java.util.List;

import org.mozzartbet.hackathon.actions.Action;
import org.mozzartbet.hackathon.bots.Bot;
import org.mozzartbet.hackathon.util.PokerConstants;

/**
 * A Texas Hold'em player. <br />
 * <br />
 *
 * The player's actions are delegated to a {@link Bot}, which can be either
 * human-controlled or AI-controlled (bot).
 */
public class Player {

    /** Name. */
    private final String name;

    /** Client application responsible for the actual behavior. */
    private final Bot bot;

    /** Hand of cards. */
    private final Hand hand;

    /** Current amount of cash. */
    private int cash;

    /** Whether the player has hole cards. */
    private boolean hasCards;

    /** Current bet. */
    private int bet;

    /** Last action performed. */
    private Action action;

    /**
     * Constructor.
     *
     *
     *            The player's name.
     * @param cash
     *            The player's starting amount of cash.
     * @param bot
     *            The bot application.
     */
    public Player(int cash, Bot bot) {
        this.name = bot.getName();
        this.cash = cash;
        this.bot = bot;

        hand = new Hand();

        resetHand();
    }

    protected Player(String name, int cash, Bot bot) {
        this.name = name;
        this.cash = cash;
        this.bot = bot;

        hand = new Hand();

        resetHand();
    }


    public Player(Bot bot) {
        this.name = bot.getName();
        this.cash = PokerConstants.STARTING_CASH;
        this.bot = bot;

        hand = new Hand();

        resetHand();
    }

    /**
     * Returns the bot.
     *
     * @return The bot.
     */
    public Bot getBot() {
        return bot;
    }

    /**
     * Prepares the player for another hand.
     */
    public void resetHand() {
        hasCards = false;
        hand.removeAllCards();
        resetBet();
    }

    /**
     * Resets the player's bet.
     */
    public void resetBet() {
        bet = 0;
        action = (hasCards() && cash == 0) ? Action.ALL_IN : null;
    }

    /**
     * Sets the hole cards.
     */
    public void setCards(List<Card> cards) {
        hand.removeAllCards();
        if (cards != null) {
            if (cards.size() == 2) {
                hand.addCards(cards);
                hasCards = true;
            } else {
                throw new IllegalArgumentException("Invalid number of cards");
            }
        }
    }

    /**
     * Returns whether the player has his hole cards dealt.
     *
     * @return True if the hole cards are dealt, otherwise false.
     */
    public boolean hasCards() {
        return hasCards;
    }

    /**
     * Returns the player's name.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the player's current amount of cash.
     *
     * @return The amount of cash.
     */
    public int getCash() {
        return cash;
    }

    /**
     * Returns the player's current bet.
     *
     * @return The current bet.
     */
    public int getBet() {
        return bet;
    }

    /**
     * Sets the player's current bet.
     *
     * @param bet
     *            The current bet.
     */
    public void setBet(int bet) {
        this.bet = bet;
    }

    /**
     * Returns the player's most recent action.
     *
     * @return The action.
     */
    public Action getAction() {
        return action;
    }

    /**
     * Sets the player's most recent action.
     *
     * @param action
     *            The action.
     */
    public void setAction(Action action) {
        this.action = action;
    }

    /**
     * Indicates whether this player is all-in.
     *
     * @return True if all-in, otherwise false.
     */
    public boolean isAllIn() {
        return hasCards() && (cash == 0);
    }

    /**
     * Returns the player's hole cards.
     *
     * @return The hole cards.
     */
    public Card[] getCards() {
        return hand.getCards();
    }

    /**
     * Posts the small blind.
     *
     * @param blind
     *            The small blind.
     */
    public void postSmallBlind(int blind) {
        action = Action.SMALL_BLIND;
        cash -= blind;
        bet += blind;
    }

    /**
     * Posts the big blinds.
     *
     * @param blind
     *            The big blind.
     */
    public void postBigBlind(int blind) {
        action = Action.BIG_BLIND;
        cash -= blind;
        bet += blind;
    }

    /**
     * Pays an amount of cash.
     *
     * @param amount
     *            The amount of cash to pay.
     */
    public void payCash(int amount) {
        if (amount > cash) {
            throw new IllegalStateException("Player asked to pay more cash than he owns!");
        }
        cash -= amount;
    }

    /**
     * Wins an amount of money.
     *
     * @param amount
     *            The amount won.
     */
    public void win(int amount) {
        cash += amount;
    }

    /**
     * Returns a clone of this player with only public information.
     *
     * @return The cloned player.
     */
    public Player publicClone() {
        Player clone = new Player(name, cash, null);
        clone.hasCards = hasCards;
        clone.bet = bet;
        clone.action = action;
        return clone;

    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return name;
    }

}
