package jdbc;

import java.util.List;
import java.util.Scanner;

public class main {
	public static void main(String[] args) throws ClassNotFoundException {
		Scanner scan = new Scanner(System.in);
		int opcao = 0;

		do {
			System.out.println("=-=-= N-REPORT =-=-=");
			System.out.println("1 - Reu");
			System.out.println("2 - Ocorrencia");
			System.out.println("3 - Delegado");
			System.out.println("4 - Criar tabelas");
			System.out.println("0 - Encerrar");

			opcao = scan.nextInt();

			switch (opcao) {
				case 1 -> {
					menuReu(scan);
					break;
				}
				case 2 -> {
					System.out.println("Ocorrência em desenvolvimento");
					break;
				}
				case 3 -> {
					menuDelegado(scan);
					break;
				}
				case 4 -> {
					criarTabelas(scan);
					break;
				}
			}
		} while (opcao != 0);
	}

	private static void menuReu(Scanner scan) {
		int opt = 0;
		ReuDAO reuDao;
		try {
			reuDao = new ReuDAO();
		} catch (ClassNotFoundException e) {
			System.out.println("Erro ao conectar ao banco de dados.");
			return;
		}

		do {
			System.out.println("=-=-= REU =-=-=");
			System.out.println("1 - Cadastrar");
			System.out.println("2 - Listar");
			System.out.println("3 - Listar por ID");
			System.out.println("4 - Atualizar");
			System.out.println("5 - Deletar");
			System.out.println("0 - Voltar");

			opt = scan.nextInt();

			switch (opt) {
				case 1 -> {
					cadastrarReu(scan, reuDao);
					break;
				}
				case 2 -> {
					listarReus(reuDao);
					break;
				}
				case 3 -> {
					visualizarReuPorId(scan, reuDao);
					break;
				}
				case 4 -> {
					atualizarReu(scan, reuDao);
					break;
				}
				case 5 -> {
					deletarReu(scan, reuDao);
					break;
				}
			}
		} while (opt != 0);
	}

	private static void cadastrarReu(Scanner scan, ReuDAO reuDao) {
		System.out.print("NOME: ");
		String nome = scan.next();
		System.out.print("CPF: ");
		String cpf = scan.next();
		System.out.print("NASCIMENTO: ");
		String nascimento = scan.next();

		Reu reu = new Reu(nome, cpf, nascimento);
		reuDao.inserirReu(reu);
	}

	private static void listarReus(ReuDAO reuDao) {
		List<Reu> reus = reuDao.buscarTodos();
		reus.forEach(reu -> System.out.println(
				"ID: " + reu.getId() + "\n" +
						"NOME: " + reu.getNome() + "\n" +
						"CPF: " + reu.getCpf() + "\n" +
						"NASCIMENTO: " + reu.getNascimento() + "\n"
		));
	}

	private static void visualizarReuPorId(Scanner scan, ReuDAO reuDao) {
		System.out.println("Digite o ID do Reu que deseja visualizar: ");
		int idReu = scan.nextInt();
		Reu reuEncontrado = reuDao.buscarPorId(idReu);
		if (reuEncontrado.getNome() == null) {
			System.out.println("Reu não encontrado!");
		} else {
			System.out.println("Codigo: " + reuEncontrado.getId());
			System.out.println("Nome: " + reuEncontrado.getNome());
			System.out.println("CPF: " + reuEncontrado.getCpf());
			System.out.println("Nascimento: " + reuEncontrado.getNascimento());
		}
	}

	private static void atualizarReu(Scanner scan, ReuDAO reuDao) {
		listarReus(reuDao);
		System.out.println("Digite o ID do Reu que deseja atualizar: ");
		int idReu = scan.nextInt();
		Reu reuAtualizado = reuDao.buscarPorId(idReu);

		System.out.println("Nome: ");
		String nome = scan.next();
		reuAtualizado.setNome(nome);

		System.out.println("Cpf: ");
		String cpf = scan.next();
		reuAtualizado.setCpf(cpf);

		System.out.println("Nascimento: ");
		String nascimento = scan.next();
		reuAtualizado.setNascimento(nascimento);

		reuDao.alterar(reuAtualizado);

		System.out.println("Codigo: " + reuAtualizado.getId());
		System.out.println("Nome: " + reuAtualizado.getNome());
		System.out.println("CPF: " + reuAtualizado.getCpf());
		System.out.println("Nascimento: " + reuAtualizado.getNascimento());
	}

	private static void deletarReu(Scanner scan, ReuDAO reuDao) {
		listarReus(reuDao);
		System.out.println("Digite o ID do Reu que deseja deletar: ");
		int idReu = scan.nextInt();
		reuDao.deletar(idReu);
		System.out.println("Reu deletado com sucesso!");
	}

	private static void menuDelegado(Scanner scan) {
		int opt = 0;
		DelegadoDAO delegadoDao;
		try {
			delegadoDao = new DelegadoDAO();
		} catch (ClassNotFoundException e) {
			System.out.println("Erro ao conectar ao banco de dados.");
			return;
		}

		do {
			System.out.println("=-=-= DELEGADO =-=-=");
			System.out.println("1 - Cadastrar");
			System.out.println("2 - Listar");
			System.out.println("3 - Listar por ID");
			System.out.println("4 - Atualizar");
			System.out.println("5 - Deletar");
			System.out.println("0 - Voltar");

			opt = scan.nextInt();

			switch (opt) {
				case 1 -> {
					cadastrarDelegado(scan, delegadoDao);
					break;
				}
				case 2 -> {
					listarDelegados(delegadoDao);
					break;
				}
				case 3 -> {
					visualizarDelegadoPorId(scan, delegadoDao);
					break;
				}
				case 4 -> {
					atualizarDelegado(scan, delegadoDao);
					break;
				}
				case 5 -> {
					deletarDelegado(scan, delegadoDao);
					break;
				}
			}
		} while (opt != 0);
	}

	private static void cadastrarDelegado(Scanner scan, DelegadoDAO delegadoDao) {
		System.out.print("MATRICULA: ");
		String matricula = scan.next();
		System.out.print("NOME: ");
		String nome = scan.next();
		System.out.print("CONTATO: ");
		String contato = scan.next();

		Delegado delegado = new Delegado(matricula, nome, contato);
		delegadoDao.inserirDelegado(delegado);
	}

	private static void listarDelegados(DelegadoDAO delegadoDao) {
		List<Delegado> delegados = delegadoDao.buscarTodos();
		delegados.forEach(delegado -> System.out.println(
				"ID: " + delegado.getId() + "\n" +
						"MATRICULA: " + delegado.getMatricula() + "\n" +
						"NOME: " + delegado.getNome() + "\n" +
						"CONTATO: " + delegado.getContato() + "\n"
		));
	}

	private static void visualizarDelegadoPorId(Scanner scan, DelegadoDAO delegadoDao) {
		System.out.println("Digite o ID do Delegado que deseja visualizar: ");
		int idDelegado = scan.nextInt();
		Delegado delegadoEncontrado = delegadoDao.buscarPorId(idDelegado);
		if (delegadoEncontrado.getNome() == null) {
			System.out.println("Delegado não encontrado!");
		} else {
			System.out.println("Codigo: " + delegadoEncontrado.getId());
			System.out.println("Matrícula: " + delegadoEncontrado.getMatricula());
			System.out.println("Nome: " + delegadoEncontrado.getNome());
			System.out.println("Contato: " + delegadoEncontrado.getContato());
		}
	}

	private static void atualizarDelegado(Scanner scan, DelegadoDAO delegadoDao) {
		listarDelegados(delegadoDao);
		System.out.println("Digite o ID do Delegado que deseja atualizar: ");
		int idDelegado = scan.nextInt();
		Delegado delegadoAtualizado = delegadoDao.buscarPorId(idDelegado);

		System.out.println("Matrícula: ");
		String matricula = scan.next();
		delegadoAtualizado.setMatricula(matricula);

		System.out.println("Nome: ");
		String nome = scan.next();
		delegadoAtualizado.setNome(nome);

		System.out.println("Contato: ");
		String contato = scan.next();
		delegadoAtualizado.setContato(contato);

		delegadoDao.alterar(delegadoAtualizado);

		System.out.println("Codigo: " + delegadoAtualizado.getId());
		System.out.println("Matrícula: " + delegadoAtualizado.getMatricula());
		System.out.println("Nome: " + delegadoAtualizado.getNome());
		System.out.println("Contato: " + delegadoAtualizado.getContato());
	}

	private static void deletarDelegado(Scanner scan, DelegadoDAO delegadoDao) {
		listarDelegados(delegadoDao);
		System.out.println("Digite o ID do Delegado que deseja deletar: ");
		int idDelegado = scan.nextInt();
		delegadoDao.deletar(idDelegado);
		System.out.println("Delegado deletado com sucesso!");
	}

	private static void criarTabelas(Scanner scan) {
		System.out.println("=-=-= CRIAR TABELA =-=-=");
		System.out.println("1 - Reu");
		System.out.println("2 - Ocorrencia");
		System.out.println("3 - Delegado");
		System.out.println("0 - Cancelar");
		int tabela = scan.nextInt();

		DBDAO dbDao;
		try {
			dbDao = new DBDAO();
		} catch (ClassNotFoundException e) {
			System.out.println("Erro ao conectar ao banco de dados.");
			return;
		}

		switch (tabela) {
			case 1 -> {
				dbDao.criarTabelaReu();
				break;
			}
			case 2 -> {
				System.out.println("Criacao da tabela de Ocorrencias em desenvolvimento!");
				break;
			}
			case 3 -> {
				System.out.println("Criacao da tabela de delegado em desenvolvimento!");
				break;
			}
		}
	}
}
