package br.com.fogaca.RegistroPonto.service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fogaca.RegistroPonto.model.Colaborador;
import br.com.fogaca.RegistroPonto.model.Registro;
import br.com.fogaca.RegistroPonto.repository.ColaboradorRepository;

@Service
public class CalculaBancoService {
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	
	public LocalTime calculaBanco(Long colaboradorId, List<Registro> registros) {
		Colaborador colaborador = colaboradorRepository.findById(colaboradorId).get();
		long banco = 0;
		
		long trab = ChronoUnit.MINUTES.between(colaborador.getHoraEntra(), colaborador.getHoraSai());
		long intervalo = colaborador.getIntervaloTempo().getHour() * 60 + colaborador.getIntervaloTempo().getMinute();
		long jornadaDia = trab - intervalo;
		Map<LocalDate, List<LocalTime>> registroDia = new HashMap<>();
		
		Set<LocalDate> datas = new HashSet<>();		
		
		for (Registro registro : registros) {
			datas.add(registro.getData());
		}
		
		Map<LocalDate, List<LocalTime>> registrosDia = new HashMap<>();
		for (LocalDate data : datas) {			
			List<Registro> listaRegistros = registros.stream().filter(reg -> reg.getData().isEqual(data)).collect(Collectors.toList());
			List<LocalTime> listaPontos = new ArrayList<>();
			for (Registro reg : listaRegistros) {
				listaPontos.add(reg.getHora());
			}
			registrosDia.put(data, listaPontos);
		}	
		
		for (LocalDate data : datas) {
			List<LocalTime> pontos = registrosDia.get(data);
			long minutosTrab = 0;
			long hora1 = 0;
		    long hora2 = 0;
			if(pontos.size()==4) {
				hora1 = ChronoUnit.MINUTES.between(pontos.get(0), pontos.get(1));
				hora2 = ChronoUnit.MINUTES.between(pontos.get(2), pontos.get(3));
			}			
			minutosTrab = hora1+hora2-jornadaDia;
			
			banco+=minutosTrab;
		}		
		
		LocalTime saldoAcumulado = colaborador.getSaldoAcumulado();
		saldoAcumulado = saldoAcumulado != null ? saldoAcumulado : LocalTime.of(0, 0, 0);
		long saldoAcumuladoLong = colaborador.getSaldoAcumulado().getHour() * 60 + colaborador.getSaldoAcumulado().getMinute();
		saldoAcumuladoLong+=banco;
		
		Long hora = saldoAcumuladoLong/60;
		Long minutos = saldoAcumuladoLong%60;
		
		String horaString = hora.toString();
		String minutoString = minutos.toString();
		
		String bancoString;
		if(horaString.length()==1 && minutoString.length()==1) {
			bancoString = "0"+horaString+":"+"0"+minutoString+":00";
		} else if(horaString.length()==1) {
			bancoString = "0"+horaString+":"+minutoString+":00";
		} else if(minutoString.length()==1) {
			bancoString = horaString+":"+"0"+minutoString+":00";
		}
		else {
			bancoString = horaString+":"+minutoString+":00";
		}
		LocalTime bancoLt = LocalTime.parse(bancoString);
		return bancoLt;

	}
	
	
}
