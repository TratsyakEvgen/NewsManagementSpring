package by.htp.ex.service;

import java.util.List;

import by.htp.ex.entity.Content;
import by.htp.ex.entity.Locale;

public interface ContentService {
	public List<Content> getNewslineByLocale(Locale locale);
}
