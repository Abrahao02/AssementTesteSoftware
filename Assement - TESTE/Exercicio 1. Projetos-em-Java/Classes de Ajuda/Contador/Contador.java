/*
The MIT License (MIT)

Copyright (c) 2017 Wolfgang Almeida

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

import java.time.*;

// VERSÃO 1.1

public class Contador extends Thread {
	// Propriedades privadas
	// =====================
	private int time = 0;
	private int limit = -1;
	private boolean running = true;

	// Construtores da classe
	// ======================
	public Contador(int limit) {
		this.limit = limit;

		if(this.limit <= 0) {
			this.limit = 0;
		}
	}

	public Contador() {
		this.limit = -1;
	}

	// Métodos públicos
	// ================

	// Iniciando Thread
	// ================
	@Override
	public void run() {
		while(running) {
			time += 1;

			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
				running = false;
			}

			if(limit != -1 && time == limit) {
				running = false;
			}
		}
	}

	// Resgatando o tempo em segundos
	// ==============================
	public int getTime() {
		return time;
	}

	// Resgatando o tempo formatado
	// ============================
	public String getTimeFormatted() {
		LocalTime t = LocalTime.ofSecondOfDay(time);
		return t.toString();
	}

	// Parando Thread
	// ==============
	public void stopCounter() {
		running = false;
	}

	// Verificando se o contador ainda está ativo
	// ==========================================
	public boolean isRunning() {
		return running;
	}
}
