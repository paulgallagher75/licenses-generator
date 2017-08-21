/*
 * Copyright 2016-2017 Red Hat, Inc, and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.snowdrop.licenses;

import hudson.maven.MavenEmbedder;
import hudson.maven.MavenEmbedderException;
import hudson.maven.MavenRequest;
import org.apache.maven.DefaultMaven;
import org.apache.maven.Maven;
import org.eclipse.aether.RepositorySystemSession;

import java.io.File;

/**
 * @author <a href="mailto:gytis@redhat.com">Gytis Trikleris</a>
 */
class SnowdropMavenEmbedder extends MavenEmbedder {

    public SnowdropMavenEmbedder(File mavenHome, MavenRequest mavenRequest) throws MavenEmbedderException {
        super(mavenHome, mavenRequest);
    }

    public SnowdropMavenEmbedder(ClassLoader mavenClassLoader, ClassLoader parent, MavenRequest mavenRequest)
            throws MavenEmbedderException {
        super(mavenClassLoader, parent, mavenRequest);
    }

    public SnowdropMavenEmbedder(ClassLoader mavenClassLoader, MavenRequest mavenRequest)
            throws MavenEmbedderException {
        super(mavenClassLoader, mavenRequest);
    }

    public RepositorySystemSession buildRepositorySystemSession() throws Exception {
        DefaultMaven defaultMaven = (DefaultMaven) super.getPlexusContainer()
                .lookup(Maven.class);
        return defaultMaven.newRepositorySession(super.buildMavenExecutionRequest(super.getMavenRequest()));
    }

}
