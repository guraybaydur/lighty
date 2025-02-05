/*
 * Copyright (c) 2021 PANTHEON.tech s.r.o. All Rights Reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-v10.html
 */
package io.lighty.applications.rnc.module;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertFalse;

import io.lighty.applications.rnc.module.config.RncLightyModuleConfigUtils;
import io.lighty.applications.rnc.module.config.RncLightyModuleConfiguration;
import io.lighty.core.controller.impl.config.ConfigurationException;
import org.testng.annotations.Test;

public class RncLightyModuleSmokeTest {
    private static final int MODULE_TIMEOUT = 60;

    @Test
    public void rncLightyModuleSmokeTest() throws ConfigurationException {
        final RncLightyModule rncModule = new RncLightyModule(RncLightyModuleConfigUtils.loadDefaultConfig(),
                MODULE_TIMEOUT);
        rncModule.initModules();
        rncModule.close();
    }

    @Test
    public void rncLightyModuleStartFailed() throws ConfigurationException {
        final RncLightyModuleConfiguration config = spy(RncLightyModuleConfigUtils.loadDefaultConfig());
        when(config.getControllerConfig()).thenReturn(null);
        RncLightyModule rncModule = new RncLightyModule(config, MODULE_TIMEOUT);
        final Boolean isStarted = rncModule.initModules();

        assertFalse(isStarted);
    }
}
