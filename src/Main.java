import java.util.Scanner;

import boleia.*;

public class Main {

	public static final String AJUDA = "AJUDA";
	public static final String SAI = "SAI";
	public static final String TERMINA = "TERMINA";
	public static final String REGISTA = "REGISTA";
	public static final String ENTRADA = "ENTRADA";
	public static final String NOVA = "NOVA";
	public static final String LISTA = "LISTA";
	public static final String CONSULTA = "CONSULTA";
	public static final String BOLEIA = "BOLEIA";
	public static final String REMOVE = "REMOVE";

	private static final String INCORRECT_PASSWORD = "Password incorrecta.";
	private static final String NOT_REGISTERED = "Registo nao efetuado.";
	private static final String HASUSER = "Utilizador ja existente.";
	private static final String HAS_NOT_USER = "Utilizador nao existente.";
	private static final String INVALID_COMMAND = "Comando inexistente.";
	private static final String REGISTER = "Registo efetuado.";
	private static final String MOVE_REGISTERED = "Deslocacao registada. Obrigado ";
	private static final String EXIT_PROGRAM = "Obrigado. Ate a proxima.";
	private static final String NAME = "nome (maximo 50 caracteres):";
	private static final String PASS = " password (entre 3 e 5 caracteres - digitos e letras): ";
	private static final String PASS1 = "password (entre 3 e 5 caracteres - digitos e letras): ";
	private static final String PASS2 = "password: ";
	private static final String HAS_MOVE = " ja tem uma deslocacao registada nesta data.";
	private static final String INVALID_DATA = "Dados invalidos.";
	private static final String HAS_NOT_MOVE = "Deslocacao nao registada.";
	private static final String USER_HAS_NOT_MOVE = " nao tem deslocacoes registadas.";
	private static final String NUM_RIDE_REGISTERED = "\nBoleias registadas: ";
	private static final String INVALID_DATE = "Data invalida.";
	private static final String HAS_NOT_MOVE_FOR = " nao existem deslocacoes registadas para ";
	private static final String MOVE_DOES_NOT_EXIST = "Deslocacao nao existe.";
	private static final String CAN_NOT_RIDE_HIMSELF = " nao pode dar boleia a si propria. Boleia nao registada.";
	private static final String DOES_NOT_HAVE_SEATS = " nao existe lugar. Boleia nao registada.";
	private static final String UNEXISTENT_USER = "Utilizador inexistente.";
	private static final String RIDE_REGISTERED = "Boleia registada.";
	private static final String CAN_NOT_REMOVE = " ja nao pode eliminar esta deslocacao.";
	private static final String REMOVED = "Deslocacao removida.";

	private static final String HELP1 = "ajuda - Mostra os comandos existentes\n"
			+ "termina - Termina a execucao do programa\n" + "regista - Regista um novo utilizador no programa\n"
			+ "entrada - Permite a entrada (\"login\") dum utilizador no programa";
	private static final String HELP2 = "ajuda - Mostra os comandos existentes\n"
			+ "sai - Termina a sessao deste utilizador no programa\n" + "nova - Regista uma nova deslocacao\n"
			+ "lista - Lista todas ou algumas deslocacoes registadas\n"
			+ "boleia - Regista uma boleia para uma dada deslocacao\n"
			+ "consulta - Lista a informacao de uma dada deslocacao\n" + "remove - Retira uma dada deslocacao";

	public static void main(String[] args) {
		Boleia boleia = new Boleia();
		Scanner in = new Scanner(System.in);
		String option = "";
		while (!option.equals("TERMINA") || boleia.getSession()) {
			if (boleia.getSession()) {
				System.out.print(boleia.getLoggedUser().getMail() + " > ");
			} else {
				System.out.print("> ");
			}
			option = in.next().toUpperCase();
			executeOption(option, boleia, in);
		}
		in.close();
	}

	private static void executeOption(String option, Boleia boleia, Scanner in) {
		if (!boleia.getSession()) {
			switch (option) {
			case AJUDA:
				help(boleia);
				break;

			case TERMINA:
				exitProgram(boleia);
				break;

			case REGISTA:
				register(boleia, in);
				break;

			case ENTRADA:
				login(boleia, in);
				break;

			default:
				System.out.println(INVALID_COMMAND);
				in.nextLine();

			}
		} else if (boleia.getSession()) {
			switch (option) {
			case AJUDA:
				help(boleia);
				break;

			case SAI:
				endSession(boleia);
				break;

			case NOVA:
				move(boleia, in);
				break;

			case CONSULTA:
				consult(boleia, in);
				break;
			case BOLEIA:
				ride(boleia, in);
				break;
			case REMOVE:
				remove(boleia, in);
				break;
			case LISTA:
				list(boleia, in);
				break;

			default:
				System.out.println(INVALID_COMMAND);
				in.nextLine();

			}
		}
	}

	private static void exitProgram(Boleia boleia) {
		System.out.println(EXIT_PROGRAM);
	}

	private static void endSession(Boleia boleia) {
		boleia.setSession(false);
		System.out.println(EXIT_PROGRAM);
	}

	private static void help(Boleia boleia) {
		if (!boleia.getSession())
			System.out.println(HELP1);
		else {
			System.out.println(HELP2);
		}
	}

	private static void register(Boleia boleia, Scanner in) {
		String mail = in.nextLine().trim();
		if (boleia.hasUser(mail)) {
			System.out.println(HASUSER);
			System.out.println(NOT_REGISTERED);
		} else {
			System.out.print(NAME);
			String name = in.nextLine().trim();
			System.out.print(PASS);
			String password = "";
			for (int max = 3; max >= 1; max--) {
				password = in.nextLine().trim();
				if (boleia.isPassValid(password)) {
					break;
				} else {
					if (max == 3 || max == 2) {
						System.out.println(INCORRECT_PASSWORD);
						System.out.print(PASS1);
					}
				}
			}
			if (!boleia.isPassValid(password)) {
				System.out.println(INCORRECT_PASSWORD);
				System.out.println(NOT_REGISTERED);

			} else if (boleia.getSession()) {
				System.out.println(INVALID_COMMAND);
			} else {
				boleia.addUser(mail, name, password);
				System.out.println(REGISTER);
			}
		}
	}

	private static void login(Boleia boleia, Scanner in) {
		String mail = in.nextLine().trim();
		if (!boleia.hasUser(mail)) {
			System.out.println(HAS_NOT_USER);
		} else {
			String password = "";
			System.out.print(PASS2);
			for (int max = 3; max >= 1; max--) {
				password = in.nextLine().trim();
				if (password.equals(boleia.equalsPass(mail))) {
					break;
				} else {
					if (max == 3 || max == 2) {
						System.out.println(INCORRECT_PASSWORD);
						System.out.print(PASS2);
					}
				}
			}
			if (!password.equals(boleia.equalsPass(mail))) {
				System.out.println(INCORRECT_PASSWORD);

			}

			else {
				boleia.setSession(true);
				boleia.login(boleia.getUser(mail));
			}
		}
	}

	private static void move(Boleia boleia, Scanner in) {
		in.nextLine();
		String origin = in.nextLine().trim();
		String destination = in.nextLine().trim();
		String date = in.next().trim();
		int hour = in.nextInt();
		double duration = in.nextDouble();
		int numSeats = in.nextInt();
		in.nextLine();
		BasicDate data = new BasicDate(date);
		if (data.isValid() && hour >= 0 && hour <= 23 && duration > 0 && numSeats > 0) {
			if (boleia.getLoggedUser().hasMove(date))
				System.out.println(boleia.getLoggedUser().getName() + HAS_MOVE);
			else {
				System.out.println(MOVE_REGISTERED + boleia.getLoggedUser().getName() + ".");
				boleia.getLoggedUser().addMove(origin, destination, date, hour, duration, numSeats,
						boleia.getLoggedUser());
			}
		} else {
			System.out.println(INVALID_DATA);
			System.out.println(HAS_NOT_MOVE);
		}

	}

	private static void list(Boleia boleia, Scanner in) {
		String date = in.nextLine().trim();
		if (date.isEmpty()) {
			list(boleia);
		} else {
			listDate(boleia, date);
		}
	}

	private static void list(Boleia boleia) {

		if (!boleia.getLoggedUser().hasMoves()) {
			System.out.println(boleia.getLoggedUser().getName() + USER_HAS_NOT_MOVE);
		} else {
			IteratorDate it = boleia.listDate();
			while (it.hasNext()) {
				Move tmp = it.next();
				System.out.println(tmp.consultAll() + NUM_RIDE_REGISTERED
						+ boleia.getLoggedUser().getBoleias(tmp.getDate()) + "\n");
			}
		}
	}

	private static void listDate(Boleia boleia, String date) {
		BasicDate data = new BasicDate(date);
		if (!data.isValid()) {
			System.out.println(INVALID_DATE);
		} else if (!boleia.hasMove(date)) {
			System.out.println(boleia.getLoggedUser().getName() + HAS_NOT_MOVE_FOR + date + ".");
		} else {
			IteratorUser it = boleia.listUsers(date);
			while (it.hasNext()) {
				User tmp = it.next();
				if (tmp.hasMove(date)) {
					System.out.println(tmp.getMail() + "\n" + tmp.getMove(date).consultAll() + NUM_RIDE_REGISTERED
							+ tmp.getBoleias(date) + "\n");
				}
			}
		}
	}

	private static void consult(Boleia boleia, Scanner in) {
		String mail = in.next().trim();
		String date = in.next().trim();
		BasicDate data = new BasicDate(date);
		if (!boleia.getUser(mail).hasMove(date)) {
			System.out.println(MOVE_DOES_NOT_EXIST);
		} else if (!boleia.hasUser(mail)) {
			System.out.println(HAS_NOT_USER);
		} else if (!data.isValid()) {
			System.out.println(INVALID_DATE);
		} else {
			System.out.println(boleia.getUser(mail).getMove(date).consult());
		}

	}

	private static void ride(Boleia boleia, Scanner in) {
		String mail = in.next().trim();
		String date = in.nextLine().trim();
		BasicDate data = new BasicDate(date);

		if (!boleia.getUser(mail).hasMove(date)) {
			System.out.println(MOVE_DOES_NOT_EXIST);
		} else if (boleia.getLoggedUser().getMail().equals(boleia.getUser(mail).getMail())) {
			System.out.println(boleia.getLoggedUser().getName() + CAN_NOT_RIDE_HIMSELF);
		}

		else if (!boleia.getUser(mail).getMove(date).hasEmptySeats()) {
			System.out.println(boleia.getLoggedUser().getName() + DOES_NOT_HAVE_SEATS);
		} else if (!boleia.hasUser(mail)) {
			System.out.println(UNEXISTENT_USER);
		} else if (!data.isValid()) {
			System.out.println(INVALID_DATE);
		} else {
			boleia.getUser(mail).getMove(date).decEmptySeats();
			System.out.println(RIDE_REGISTERED);

		}
	}

	private static void remove(Boleia boleia, Scanner in) {
		String date = in.next().trim();
		BasicDate data = new BasicDate(date);
		if (!boleia.getLoggedUser().hasMove(date)) {
			System.out.println(MOVE_DOES_NOT_EXIST);
		} else if (boleia.getLoggedUser().getMove(date).getNumSeats() != boleia.getLoggedUser().getMove(date)
				.getEmptySeats()) {
			System.out.println(boleia.getLoggedUser().getName() + CAN_NOT_REMOVE);
		} else if (!data.isValid()) {
			System.out.println(INVALID_DATE);
		} else {
			boleia.getLoggedUser().getMove(date).incEmptySeats();
			boleia.getLoggedUser().remove(date);
			System.out.println(REMOVED);
		}
	}

}