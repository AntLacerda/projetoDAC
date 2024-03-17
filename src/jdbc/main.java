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
			
			switch(opcao) {
				case 1 -> {
					int opt = 0;
					
					do {
						System.out.println("=-=-= REU =-=-=");
						System.out.println("1 - Cadastrar");
						System.out.println("2 - Listar");
						System.out.println("3 - Listar por ID");
						System.out.println("4 - Atualizar");
						System.out.println("5 - Deletar");
						System.out.println("0 - Voltar");
						
						opt = scan.nextInt();
						
						switch(opt) {
							case 1 -> {
								System.out.print("NOME: ");
								String nome = scan.next();
								System.out.print("CPF: ");
								String cpf = scan.next();
								System.out.print("NASCIMENTO: ");
								String nascimento = scan.next();
								
								Reu reu = new Reu(nome, cpf, nascimento);
								
								ReuDAO reuDao = new ReuDAO();
								reuDao.inserirReu(reu);
								
								break;
							}
						
							case 2 -> {
								ReuDAO reuDao = new ReuDAO();
								List<Reu> reus = reuDao.buscarTodos();
								reus.forEach((reuUnico -> 
													System.out.println(
														"ID: " + reuUnico.getId() + "\n" 
														+ "NOME: " + reuUnico.getNome() + "\n" 
														+ "CPF: " + reuUnico.getCpf() + "\n"
														+ "NASCIMENTO: " + reuUnico.getNascimento() + "\n"
													)
											));
								break;
							}
							
							case 3 -> {
								ReuDAO reuDao = new ReuDAO();
								System.out.println("Digite o ID do Reu que deseja visualizar: ");
								int idReu = scan.nextInt();
					
								Reu reuEncontrado = reuDao.buscarPorId(idReu);
								if(reuEncontrado.getNome() == null) {
									System.out.println("Reu nao encontrado!");
								} else {
									System.out.println("Codigo: " + reuEncontrado.getId());
									System.out.println("Nome: " + reuEncontrado.getNome());
									System.out.println("CPF: " + reuEncontrado.getCpf());
									System.out.println("Nascimento: " + reuEncontrado.getNascimento());
								}
								break;
							}
							
							case 4 -> {
								ReuDAO reuDao = new ReuDAO();
								List<Reu> reus = reuDao.buscarTodos();
								reus.forEach((reuUnico -> 
													System.out.println(
														"ID: " + reuUnico.getId() + "\n" 
														+ "NOME: " + reuUnico.getNome() + "\n" 
													)
											));
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
	
								break;
							}
							
							case 5 -> {
								ReuDAO reuDao = new ReuDAO();
								List<Reu> reus = reuDao.buscarTodos();
								reus.forEach((reuUnico -> 
													System.out.println(
														"ID: " + reuUnico.getId() + "\n" 
														+ "NOME: " + reuUnico.getNome() + "\n" 
													)
											));
								System.out.println("Digite o ID do Reu que deseja deletar: ");
								int idReu = scan.nextInt();
								
								reuDao.deletar(idReu);
								
								System.out.println("Reu deletado com sucesso!");
								break;
							}
					}
				}while(opt!=0);
				break;
			}
			
			case 2 -> {
				System.out.println("Ocorrencia em desenvolvimento");
				break;
			}
			
			case 3 -> {
				System.out.println("Delegado em desenvolvimento");
				break;
			}
			
			case 4 -> {
				System.out.println("=-=-= CRIAR TABELA =-=-=");
				System.out.println("1 - Reu");
				System.out.println("2 - Ocorrencia");
				System.out.println("3 - Delegado");
				System.out.println("0 - Cancelar");
				int tabela = scan.nextInt();
				
				switch(tabela) {
					case 1 -> {
						DBDAO dbDao = new DBDAO();
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
				
				break;
			}
		}		
	}while(opcao!=0);
				
	}
}
