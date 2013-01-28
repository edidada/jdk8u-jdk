/*
 * Copyright (c) 2000, 2006, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package sun.security.jgss;

import java.security.Provider;
import java.security.AccessController;

/**
 * Defines the Sun JGSS provider.
 * Will merger this with the Sun security provider
 * sun.security.provider.Sun when the JGSS src is merged with the JDK
 * src.
 *
 * Mechanisms supported are:
 *
 * - Kerberos v5 as defined in RFC 1964.
 *   Oid is 1.2.840.113554.1.2.2
 *
 * - SPNEGO as defined in RFC 2478
 *   Oid is 1.3.6.1.5.5.2
 *
 *   [Dummy mechanism is no longer compiled:
 * - Dummy mechanism. This is primarily useful to test a multi-mech
 *   environment.
 *   Oid is 1.3.6.1.4.1.42.2.26.1.2]
 *
 * @author Mayank Upadhyay
 */

public final class SunProvider extends Provider {

    private static final long serialVersionUID = -238911724858694198L;

    private static final String INFO = "Sun " +
        "(Kerberos v5, SPNEGO)";
    //  "(Kerberos v5, Dummy GSS-API Mechanism)";

    public static final SunProvider INSTANCE = new SunProvider();

    public SunProvider() {
        /* We are the Sun JGSS provider */
        super("SunJGSS", 1.7d, INFO);

        AccessController.doPrivileged(
                        new java.security.PrivilegedAction<Void>() {
            public Void run() {
                put("GssApiMechanism.1.2.840.113554.1.2.2",
                    "sun.security.jgss.krb5.Krb5MechFactory");
                put("GssApiMechanism.1.3.6.1.5.5.2",
                    "sun.security.jgss.spnego.SpNegoMechFactory");
                /*
                  put("GssApiMechanism.1.3.6.1.4.1.42.2.26.1.2",
                  "sun.security.jgss.dummy.DummyMechFactory");
                */
                return null;
            }
        });
    }
}
