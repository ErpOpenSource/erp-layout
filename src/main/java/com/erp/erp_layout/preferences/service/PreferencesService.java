package com.erp.erp_layout.preferences.service;

import com.erp.erp_layout.modules.domain.UserPreferences;
import com.erp.erp_layout.modules.repository.PreferencesRepository;
import com.erp.erp_layout.preferences.dto.PreferencesDto;
import com.erp.erp_layout.preferences.dto.UpdatePreferencesDto;
import com.erp.erp_layout.preferences.mapper.PreferencesMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PreferencesService {

    private final PreferencesRepository repository;
    private final PreferencesMapper mapper;

    @Transactional(readOnly = true)
    public PreferencesDto getPreferences(String userId) {
        return repository.findByUserId(userId)
                .map(mapper::toDto)
                .orElseGet(() -> mapper.toDto(createDefault(userId)));
    }

    @Transactional
    public PreferencesDto updatePreferences(String userId, UpdatePreferencesDto dto) {
        UserPreferences prefs = repository.findByUserId(userId)
                .orElseGet(() -> createDefault(userId));

        mapper.updateFromDto(dto, prefs);
        return mapper.toDto(repository.save(prefs));
    }

    private UserPreferences createDefault(String userId) {
        UserPreferences prefs = UserPreferences.builder()
                .userId(userId)
                .theme("system")
                .language("es")
                .sidebarCollapsed(false)
                .build();
        return repository.save(prefs);
    }
}