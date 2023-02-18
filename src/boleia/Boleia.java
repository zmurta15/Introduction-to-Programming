package boleia;

public class Boleia {

	private User[] users;

	private static int DEFAULT_SIZE = 100;
	private User loggedUser; // utilizador em modo sessao
	private int counter;
	private boolean session; // verifica se esta em sessao

	public Boleia() {
		users = new User[DEFAULT_SIZE];
		counter = 0;
		session = false;
		loggedUser = null;
	}

	/**
	 * Adiciona um novo utilizador
	 * 
	 * @param mail
	 * @param name
	 * @param password
	 */
	public void addUser(String mail, String name, String password) {
		if (isUsersFull()) {
			resizeUser();
		}
		User user = new User(mail, name, password);
		users[counter++] = user;
		
	}

	public boolean hasUser(String mail) {
		boolean found = false;
		for (int i = 0; i < counter; i++) {
			if (users[i].getMail().equals(mail))
				found = true;
		}
		return found;
	}

	/**
	 * Retorna o utilizador
	 * 
	 * @pre hasUser
	 * @param mail
	 * @return user
	 */
	public User getUser(String mail) {
		User user = null;
		for (int i = 0; i < counter; i++) {
			if (users[i].getMail().equals(mail))
				user = users[i];

		}
		return user;
	}

	/**
	 * Verifica se uma password tem caracteres invalidos
	 * 
	 * @param password
	 * @return true caso nao existam, false caso contrario
	 */
	public boolean validPass(String password) {
		char[] pass = password.toCharArray();
		for (int i = 0; i < pass.length; i++) {
			if (!Character.isLetterOrDigit(pass[i]))
				return true;
		}
		return false;
	}

	/**
	 * Verifica se a password e valida tendo em conta as condicoes do enunciado
	 * 
	 * @param password
	 * @return true caso seja valida, false caso contrario
	 */
	public boolean isPassValid(String password) {
		int valid = 0;
		if (password.length() >= 3 && password.length() <= 5 && !validPass(password)) {
			valid = 1;
		}
		return valid == 1;
	}

	/**
	 * Procura uma posicao no vetor de utilizadores dado um mail como parametro
	 * 
	 * @param mail
	 * @return result
	 */
	private int searchIndex(String mail) {
		int i = 0;
		int result = -1;
		while ((i < counter) && (result == -1)) {
			if (users[i].getMail().equals(mail)) {
				result = i;
			}
			i++;
		}
		return result;
	}

	/**
	 * Verifica se a password dada ao registar coincide com a da entrada, dando um
	 * mail como parametro
	 * 
	 * @param mail
	 * @return users
	 */
	public String equalsPass(String mail) {
		return users[searchIndex(mail)].getPassword();
	}

	/**
	 * Verifica se esta em modo sessao
	 * 
	 * @return session
	 */
	public boolean getSession() {
		return session;
	}

	/**
	 * Atualiza o programa para modo sessao
	 * 
	 * @param session
	 */
	public void setSession(boolean session) {
		this.session = session;
	}

	/**
	 * Atualiza o utilizador para o utilizador em modo sessao
	 * 
	 * @param user
	 */
	public void login(User user) {
		loggedUser = user;
	}

	/**
	 * Retorna o utilizador atualmente em modo sessao
	 * 
	 * @return loggedUser
	 */
	public User getLoggedUser() {
		return loggedUser;
	}

	/**
	 * Procura uma viagem dando uma data como parametro
	 * 
	 * @param date
	 * @return move
	 */
	public Move getMove(String date) {
		Move move = null;
		for (int i = 0; i < counter; i++) {
			if (users[i].hasMove(date)) {
				move = users[i].getMove(date);
				break;
			}
		}
		return move;
	}

	/**
	 * Verifica se existe viagem dando uma data como parametro
	 * 
	 * @param date
	 * @return move
	 */
	public boolean hasMove(String date) {
		boolean move = false;
		for (int i = 0; i < counter; i++) {
			if (users[i].hasMove(date)) {
				move = true;
			}
		}
		return move;
	}

	/**
	 * Itera os utilizadores
	 * 
	 * @param date
	 * @return IteratorUser
	 */
	public IteratorUser listUsers(String date) {
		sortUsers();
		return new IteratorUser(users, counter);
	}

	public IteratorDate listDate() {
		return getLoggedUser().listDate();
	}

	/**
	 * Ordena os utilizadores por ordem alfabetica
	 */
	private void sortUsers() {
		for (int i = 0; i < counter; i++) {
			for (int j = i + 1; j < counter; j++) {
				if (users[i].getMail().compareTo(users[j].getMail()) > 0) {
					User tmp = users[j];
					users[j] = users[i];
					users[i] = tmp;
				}
			}
		}
	}

	/**
	 * Verifica se o vetor de utilizadores esta cheio
	 * 
	 * @return true caso esteja, false casjo contrario
	 */
	private boolean isUsersFull() {
		return counter == users.length - 1;
	}

	/**
	 * Aumenta a dimensao do vetor por duas vezes
	 * 
	 * @pre isUsersFull
	 */
	private void resizeUser() {
		User[] tmp = new User[2 * users.length];
		for (int i = 0; i < users.length; i++)
			tmp[i] = users[i];
		users = tmp;
	}

}
