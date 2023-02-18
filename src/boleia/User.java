package boleia;

public class User {

	private static int DEFAULT_SIZE = 100;
	private Move[] moves;
	private int counter;
	private String name, mail, password;

	public User(String mail, String name, String password) {
		moves = new Move[DEFAULT_SIZE];
		counter = 0;
		this.mail = mail;
		this.name = name;
		this.password = password;
	}

	/**
	 * Retorna o mail do utilizador
	 * 
	 * @return mai
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Retorna o nome do utilizador
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retorna a password do utilizador
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	// --------------------------------------Moves---------------------------------------//
	/**
	 * Adiciona uma nova viagem
	 * 
	 * @param origin
	 * @param destination
	 * @param date
	 * @param hours
	 * @param duration
	 * @param numSeats
	 * @param user
	 */
	public void addMove(String origin, String destination, String date, int hours, double duration, int numSeats,
			User user) {
		if (isMovesFull()) {
			resizeMoves();
		}
		Move move = new Move(origin, destination, date, hours, duration, numSeats, user);
		moves[counter++] = move;
		sortDates();
	}

	/**
	 * Procura uma viagem com a data dada
	 * 
	 * @param date
	 * @return true or false
	 */
	public boolean hasMove(String date) {
		boolean found = false;
		for (int i = 0; i < counter && !found; i++) {
			if (moves[i].getDate().equals(date)) {
				found = true;
			}
		}
		return found;
	}

	/**
	 * Devolve a viagem com a data dada como parametro
	 * 
	 * @pre: hasMove(date)
	 * @param data
	 * @return
	 */
	public Move getMove(String data) {
		Move move = null;
		for (int i = 0; i < counter; i++) {
			if (moves[i].getDate().equals(data)) {
				move = moves[i];
				break;
			}
		}
		return move;
	}

	/**
	 * Verifica se existem viagens registadas
	 * 
	 * @return counter >0
	 */
	public boolean hasMoves() {
		return counter > 0;
	}

	/**
	 * Remove a viagem de uma certa data
	 * 
	 * @pre viagem != null
	 * @param date
	 */
	public void remove(String date) {
		boolean found = false;
		for (int i = 0; i < counter - 1; i++) {
			if (moves[i].getDate().equals(date) || found) {
				moves[i] = moves[i + 1];
				found = true;
			}
		}
		counter--;
	}

	/**
	 * Itera as viagens
	 * 
	 * @return iteratorDate
	 */
	public IteratorDate listDate() {
		return new IteratorDate(moves, counter);
	}

	/**
	 * Organiza as viagens por data
	 */
	private void sortDates() {
		for (int i = 0; i < counter; i++) {
			BasicDate data1 = new BasicDate(moves[i].getDate());
			for (int j = i + 1; j < counter; j++) {
				BasicDate data2 = new BasicDate(moves[j].getDate());
				if (data1.getYear() > data2.getYear()) {
					Move tmp = moves[j];
					moves[j] = moves[i];
					moves[i] = tmp;
				} else if (data1.getYear() == data2.getYear()) {
					if (data1.getMonth() > data2.getMonth()) {
						Move tmp = moves[j];
						moves[j] = moves[i];
						moves[i] = tmp;
					} else if (data1.getMonth() == data2.getMonth()) {
						if (data1.getDay() > data2.getDay()) {
							Move tmp = moves[j];
							moves[j] = moves[i];
							moves[i] = tmp;
						}
					}
				}
			}
		}
	}

	/**
	 * Retorna as boleias registadas
	 * 
	 * @param date
	 * @return as boleias registadas
	 */
	public int getBoleias(String date) {
		return getMove(date).getNumSeats() - getMove(date).getEmptySeats();
	}

	/**
	 * Verifica se o vetor de viagens esta cheio
	 * 
	 * @return true or false
	 */
	private boolean isMovesFull() {
		return counter == moves.length - 1;
	}

	/**
	 * Aumenta a dimensao do vetor duas vezes
	 */
	private void resizeMoves() {
		Move[] tmp = new Move[2 * moves.length];
		for (int i = 0; i < moves.length; i++)
			tmp[i] = moves[i];
		moves = tmp;
	}

}
