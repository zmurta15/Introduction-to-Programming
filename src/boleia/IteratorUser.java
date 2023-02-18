package boleia;

public class IteratorUser {

	private int counter, current;
	private User[] users;

	/**
	 * Cria um novo iterador de utilizadores
	 * 
	 * @param users   - vetor de utilizadores *@pre users != null
	 * @param counter - contador de utilizadores *@pre counter <= users.length
	 */
	public IteratorUser(User[] users, int counter) {
		this.users = users;
		this.counter = counter;
	}

	/**
	 * Testa de ha mais elementos a visitar
	 * 
	 * @return true, se houver mais elementos a visitar, false caso contrario
	 */
	public boolean hasNext() {
		return current < counter;
	}

	/**
	 * Devolve o proximo utilizador do vetor
	 * 
	 * @return o proximo utilizador
	 */
	public User next() {
		return users[current++];
	}

}
