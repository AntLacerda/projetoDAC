package jdbc;

import jdbc.com.nReport.dao.*;
import jdbc.com.nReport.entities.Delegacia;
import jdbc.com.nReport.entities.Delegado;
import jdbc.com.nReport.entities.Ocorrencia;
import jdbc.com.nReport.entities.Reu;

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
				int opt = 0;

				do {
					System.out.println("=-=-= OCORRÊNCIA =-=-=");
					System.out.println("1 - Cadastrar");
					System.out.println("2 - Registrar Ocorrencia");
					System.out.println("3 - Listar");
					System.out.println("4 - Listar por ID");
					System.out.println("5 - Atualizar");
					System.out.println("6 - Deletar");
					System.out.println("0 - Voltar");

					opt = scan.nextInt();

					switch(opt) {
						case 1 -> {
							System.out.print("DATA: ");
							String data = scan.next();
							System.out.print("HORA: ");
							String hora = scan.next();
							System.out.print("LOCAL: ");
							String local = scan.next();
							System.out.print("TIPO DO CRIME: ");
							String tipoCrime = scan.next();
							DelegadoDAO delegadoDao = new DelegadoDAO();
							List<Delegado> delegados = delegadoDao.buscarTodos();
							delegados.forEach((delegadoUnico ->
									System.out.println(
											"CODIGO: " + delegadoUnico.getId() + "\n"
													+ "NOME: " + delegadoUnico.getNome() + "\n"
									)
							));
							System.out.println("Digite o CODIGO do Delegado da ocorrência: ");
							int idDelegado = scan.nextInt();

							Ocorrencia ocorrencia = new Ocorrencia(data,hora,local,tipoCrime,idDelegado);

							OcorrenciaDAO ocorrenciaDao = new OcorrenciaDAO();
							ocorrenciaDao.criarOcorrencia(ocorrencia);

							System.out.println("Ocorrencia criada com sucesso!");
							break;
						}

						case 2 -> {

							OcorrenciaDAO ocorrenciaDao = new OcorrenciaDAO();
							List<Ocorrencia> ocorrencias = ocorrenciaDao.buscarTodos();
							ocorrencias.forEach((ocorrenciaUnica ->
									System.out.println(
													"ID: " + ocorrenciaUnica.getId() + "\n"
															+ "TIPO: " + ocorrenciaUnica.getTipoCrime() + "\n"
													+ "LOCAL: " + ocorrenciaUnica.getLocal() + "\n"
									)
							));

							System.out.println("Digite o ID da Ocorrencia que deseja registrar: ");

							int respOcorrencia = scan.nextInt();

							int loop = 1;
							do {
								ReuDAO reuDao = new ReuDAO();
								List<Reu> reus = reuDao.buscarTodos();
								reus.forEach((reuUnico ->
										System.out.println(
												"ID: " + reuUnico.getId() + "\n"
														+ "NOME: " + reuUnico.getNome() + "\n"
										)
								));

								System.out.println("Digite o ID do Reu que deseja inserir na ocorrencia ou 0 para sair: ");

								loop = scan.nextInt();

								if(loop == 0){
									break;
								}

								int idReu = loop;

								Reu reu = reuDao.buscarPorId(idReu);
								Ocorrencia ocorrenciaBuscada = ocorrenciaDao.buscarPorId(respOcorrencia);

								Delegacia delegacia = new Delegacia(ocorrenciaBuscada, reu);

								DelegaciaDAO delegaciaDao = new DelegaciaDAO();

								delegaciaDao.salvar(delegacia);
							} while (loop != 0);

							break;
						}


						case 3 -> {
							OcorrenciaDAO ocorrenciaDao = new OcorrenciaDAO();
							DelegadoDAO delegadoDao = new DelegadoDAO();
							List<Ocorrencia> ocorrencias = ocorrenciaDao.buscarTodos();
							ocorrencias.forEach((ocorrenciaUnica ->
									System.out.println(
													"ID: " + ocorrenciaUnica.getId() + "\n"
													+ "DATA: " + ocorrenciaUnica.getData() + "\n"
													+ "HORA: " + ocorrenciaUnica.getHora() + "\n"
													+ "LOCAL: " + ocorrenciaUnica.getLocal() + "\n"
													+ "CRIME: " + ocorrenciaUnica.getTipoCrime() + "\n"
													+ "DELEGADO: " + delegadoDao.buscarPorId(ocorrenciaUnica.getIdDelegado()).getNome() + "\n"
									)
							));
							break;
						}

						case 4 -> {
							OcorrenciaDAO ocorrenciaDao = new OcorrenciaDAO();
							DelegadoDAO delegadoDao = new DelegadoDAO();
							System.out.println("Digite o ID da Ocorrencia que deseja visualizar: ");
							int idOcorrencia = scan.nextInt();

							Ocorrencia ocorrenciaEncontrada = ocorrenciaDao.buscarPorId(idOcorrencia);
							if(ocorrenciaEncontrada.getData() == null) {
								System.out.println("Ocorrencia nao encontrado!");
							} else {
								System.out.println(
										"ID: " + ocorrenciaEncontrada.getId() + "\n"
												+ "DATA: " + ocorrenciaEncontrada.getData() + "\n"
												+ "HORA: " + ocorrenciaEncontrada.getHora() + "\n"
												+ "LOCAL: " + ocorrenciaEncontrada.getLocal() + "\n"
												+ "CRIME: " + ocorrenciaEncontrada.getTipoCrime() + "\n"
												+ "DELEGADO: " + delegadoDao.buscarPorId(ocorrenciaEncontrada.getIdDelegado()).getNome() + "\n"
								);
							}
							break;
						}

						case 5 -> {
							OcorrenciaDAO ocorrenciaDao = new OcorrenciaDAO();
							List<Ocorrencia> ocorrencias = ocorrenciaDao.buscarTodos();
							ocorrencias.forEach((ocorrenciaUnica ->
									System.out.println(
													"CODIGO: " + ocorrenciaUnica.getId() + "\n"
													+ "CRIME: " + ocorrenciaUnica.getTipoCrime() + "\n"
													+ "LOCAL: " + ocorrenciaUnica.getLocal() + "\n"
									)
							));
							System.out.println("Digite o CODIGO da Ocorrencia que deseja atualizar: ");
							int idOcorrencia = scan.nextInt();

							Ocorrencia ocorrenciaAtualizada = ocorrenciaDao.buscarPorId(idOcorrencia);

							System.out.println("Data: ");
							String data = scan.next();
							ocorrenciaAtualizada.setData(data);

							System.out.println("Hora: ");
							String hora = scan.next();
							ocorrenciaAtualizada.setHora(hora);

							System.out.println("Local: ");
							String local = scan.next();
							ocorrenciaAtualizada.setLocal(local);

							System.out.println("Crime: ");
							String crime = scan.next();
							ocorrenciaAtualizada.setTipoCrime(crime);

							ocorrenciaDao.alterar(ocorrenciaAtualizada);

							System.out.println("Data: " + ocorrenciaAtualizada.getData());
							System.out.println("Hora: " + ocorrenciaAtualizada.getHora());
							System.out.println("Local: " + ocorrenciaAtualizada.getLocal());
							System.out.println("Crime: " + ocorrenciaAtualizada.getTipoCrime());

							break;
						}

						case 6 -> {
							OcorrenciaDAO ocorrenciaDao = new OcorrenciaDAO();
							List<Ocorrencia> ocorrencias = ocorrenciaDao.buscarTodos();
							ocorrencias.forEach((ocorrenciaUnica ->
									System.out.println(
													"ID: " + ocorrenciaUnica.getId() + "\n"
													+ "TIPO: " + ocorrenciaUnica.getTipoCrime() + "\n"
													+ "LOCAL: " + ocorrenciaUnica.getLocal() + "\n"
									)
							));
							System.out.println("Digite o ID da Ocorrencia que deseja deletar: ");
							int idOcorrencia = scan.nextInt();

							ocorrenciaDao.deletar(idOcorrencia);

							System.out.println("Ocorrencia deletada com sucesso!");
							break;
						}
					}
				}while(opt!=0);
				break;
			}
			
			case 3 -> {
				int opt = 0;

				do {
					System.out.println("=-=-= DELEGADO =-=-=");
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
							System.out.print("MATRICULA: ");
							String matricula = scan.next();
							System.out.print("CONTATO: ");
							String contato = scan.next();

							Delegado delegado = new Delegado(matricula, nome, contato);

							DelegadoDAO delegadoDao = new DelegadoDAO();
							delegadoDao.inserirDelegado(delegado);

							break;
						}

						case 2 -> {
							DelegadoDAO delegadoDao = new DelegadoDAO();
							List<Delegado> delegados = delegadoDao.buscarTodos();
							delegados.forEach((delegadoUnico ->
									System.out.println(
													"ID: " + delegadoUnico.getId() + "\n"
													+ "MATRICULA: " + delegadoUnico.getMatricula() + "\n"
													+ "NOME: " + delegadoUnico.getNome() + "\n"
													+ "CONTATO: " + delegadoUnico.getContato() + "\n"
									)
							));
							break;
						}

						case 3 -> {
							DelegadoDAO delegadoDao = new DelegadoDAO();
							System.out.println("Digite o ID do Delegado que deseja visualizar: ");
							int idDelegado = scan.nextInt();

							Delegado delegadoEncontrado = delegadoDao.buscarPorId(idDelegado);
							if(delegadoEncontrado.getNome() == null) {
								System.out.println("Delegado nao encontrado!");
							} else {
								System.out.println("CODIGO: " + delegadoEncontrado.getId());
								System.out.println("MATRICULA: " + delegadoEncontrado.getMatricula());
								System.out.println("NOME: " + delegadoEncontrado.getNome());
								System.out.println("CONTATO: " + delegadoEncontrado.getContato());
							}
							break;
						}

						case 4 -> {
							DelegadoDAO delegadoDao = new DelegadoDAO();
							List<Delegado> delegados = delegadoDao.buscarTodos();
							delegados.forEach((delegadoUnico ->
									System.out.println(
													"CODIGO: " + delegadoUnico.getId() + "\n"
													+ "MATRICULA: " + delegadoUnico.getMatricula() + "\n"
									)
							));
							System.out.println("Digite o CODIGO do Delegado que deseja atualizar: ");
							int idDelegado = scan.nextInt();

							Delegado delegadoAtualizado = delegadoDao.buscarPorId(idDelegado);

							System.out.println("Matricula: ");
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
							System.out.println("Matricula: " + delegadoAtualizado.getMatricula());
							System.out.println("Nome: " + delegadoAtualizado.getNome());
							System.out.println("Contato: " + delegadoAtualizado.getContato());

							break;
						}

						case 5 -> {
							DelegadoDAO delegadoDao = new DelegadoDAO();
							List<Delegado> delegados = delegadoDao.buscarTodos();
							delegados.forEach((delegadoUnico ->
									System.out.println(
													"ID: " + delegadoUnico.getId() + "\n"
													+ "NOME: " + delegadoUnico.getNome() + "\n"
									)
							));
							System.out.println("Digite o ID do Delegado que deseja deletar: ");
							int idDelegado = scan.nextInt();

							delegadoDao.deletar(idDelegado);

							System.out.println("Delegado deletado com sucesso!");
							break;
						}
					}
				}while(opt!=0);
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
						DBDAO dbDao = new DBDAO();
						dbDao.criarTabelaOcorrencia();
						break;
					}
					
					case 3 -> {
						DBDAO dbDao = new DBDAO();
						dbDao.criarTabelaDelegado();
					}
				}
				
				break;
			}
		}		
	}while(opcao!=0);
				
	}
}
