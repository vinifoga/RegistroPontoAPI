package br.com.fogaca.RegistroPonto.model;

import java.time.LocalTime;

public class Alerta {

	private Long codAlerta;
	private boolean horaTrabalhar;
	private LocalTime anteciparAvisoEntrada;
	private boolean horaSaidaIntervalo;
	private LocalTime anteciparAvisoSaidaIntervalo;
	private boolean horaRetornoIntervalo;
	private LocalTime anteciparAvisoRetornoIntervalo;
	private boolean horaIrCasa;
	private LocalTime anteciparAvisoSaida;
	private boolean modoAlarme;
	private boolean tocarIncessantemente;
}
