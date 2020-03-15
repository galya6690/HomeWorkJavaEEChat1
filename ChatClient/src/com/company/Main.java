package com.company;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
	aurhorization();
	}

	public static void aurhorization(){
			Scanner scanner = new Scanner(System.in);

			try {
				System.out.println("Якщо ви зареєстрований користувач введіть 2 якщо ні введіь 1");
				String yesorno = scanner.nextLine();
				if (yesorno.equals("2") || yesorno.equals("1")) {


					System.out.println("введіть логін");
					String login = scanner.nextLine();

					System.out.println("введіть пароль ");
					String password = scanner.nextLine();

					URL url = new URL(Utils.getURL() + "/auth?yesorno=" + yesorno + "&login=" + login + "&password=" + password);
					HttpURLConnection http = (HttpURLConnection) url.openConnection();

					if (http.getResponseCode() == 300) {
						System.out.println("повторіть спробу");
						System.out.println();
						aurhorization();

					} else {
						System.out.println("ви у чаті");
						System.out.println("Введіть користувача якому ви відправляєте повідомлення ");
						String to= scanner.nextLine();
						Thread th = new Thread(new GetThread());
						th.setDaemon(true);
						th.start();

						System.out.println("Введіть ваше повідомлення: ");
						while (true) {
							String text = scanner.nextLine();
							if (text.isEmpty()) break;

							Message m = new Message(login, text,to);
							int res = m.send(Utils.getURL() + "/add");

							if (res != 200) { // 200 OK
								System.out.println("HTTP error occured: " + res);
								return;
							}
						}
					}}
				else{
					System.out.println("ви ввели не коректні дані, повторіть спробу");
					System.out.println();
					aurhorization();
				}
				} catch(IOException ex){
					ex.printStackTrace();
				} finally{
					scanner.close();
				}
			}
		}

