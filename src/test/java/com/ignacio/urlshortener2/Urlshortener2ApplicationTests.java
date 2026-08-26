package com.ignacio.urlshortener2;

import com.ignacio.urlshortener2.Service.UrlService;
import com.ignacio.urlshortener2.exception.UrlNotFoundException;
import com.ignacio.urlshortener2.repository.UrlRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Urlshortener2ApplicationTests {

	@Test
	void deberiaAcortarUnaUrlValida() {

		UrlRepository repository = mock(UrlRepository.class);

		UrlService service = new UrlService(repository);

		String codigo = service.acortarUrl("https://www.google.com");

		assertNotNull(codigo);
		verify(repository).guardar(codigo, "https://www.google.com");
	}

	@Test
	void deberiaRechazarUrlSinHttp() {

		UrlRepository repository = mock(UrlRepository.class);
		UrlService service = new UrlService(repository);

		assertThrows(
				IllegalArgumentException.class,
				() -> service.acortarUrl("www.google.com")
		);
	}
	@Test
	void deberiaRechazarUrlVacia() {

		UrlRepository repository = mock(UrlRepository.class);
		UrlService service = new UrlService(repository);

		assertThrows(
				IllegalArgumentException.class,
				() -> service.acortarUrl("")
		);
	}
	@Test
	void deberiaLanzarExcepcionSiLaUrlNoExiste() {

		UrlRepository repository = mock(UrlRepository.class);
		UrlService service = new UrlService(repository);

		assertThrows(
				UrlNotFoundException.class,
				() -> service.obtenerUrl("abcdef")
		);
	}
	@Test
	void deberiaRechazarUrlConSintaxisInvalida() {

		UrlRepository repository = mock(UrlRepository.class);
		UrlService service = new UrlService(repository);

		assertThrows(
				IllegalArgumentException.class,
				() -> service.acortarUrl("http://esto es una url invalida")
		);
	}h
}