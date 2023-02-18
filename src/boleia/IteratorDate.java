package boleia;

public class IteratorDate {
	private int counter, current;
	private Move[] moves;

	/**
	 * Cria um novo Iterador de Viagens
	 * 
	 * @param moves   - vetor de viagens *@pre moves !=null
	 * @param counter - contador de viagens *@pre counter <=moves.length
	 */
	public IteratorDate(Move[] moves, int counter) {
		this.moves = moves;
		this.counter = counter;
	}

	/**
	 * Testa se ha mais elementos a visitar
	 * 
	 * @return true, se houver mais elementos a visitar, false caso contrario
	 */
	public boolean hasNext() {
		return current < counter;
	}

	/**
	 * Devolve a proxima viagem do vetor.
	 * 
	 * @return a proxima viagem
	 */
	public Move next() {
		return moves[current++];
	}

}
