package fi.jamk.wordsoccer.game;

public class Letter
{
	private final int number;
	private char sign = ' ';
	private boolean used;
	private Card.CardType cardType;
	private ILetterListener listener;

	public Letter(int number)
	{
		this.number = number;
		used = false;
	}

	public int getNumber()
	{
		return number;
	}

	public char getSign()
	{
		return sign;
	}

	public Letter setSign(char sign)
	{
		this.sign = sign;

		onChange();

		return this;
	}

	public boolean isUsed()
	{
		return used;
	}

	public Letter setUsed(boolean used)
	{
		if (isDisabled() && used)
		{
			throw new IllegalStateException("Cannot use a disabled letter.");
		}

		this.used = used;

		onChange();

		return this;
	}

	public boolean isDisabled()
	{
		return cardType == Card.CardType.RED;
	}

	public Card.CardType getCardType()
	{
		return cardType;
	}

	public Letter setCardType(Card.CardType cardType)
	{
		this.cardType = cardType;

		if (isDisabled())
		{
			used = false;
		}

		onChange();

		return this;
	}

	private void onChange()
	{
		if (listener != null)
		{
			listener.onChanged();
		}
	}

	public Letter setListener(ILetterListener listener)
	{
		this.listener = listener;

		return this;
	}

	public interface ILetterListener
	{
		public void onChanged();
	}
}