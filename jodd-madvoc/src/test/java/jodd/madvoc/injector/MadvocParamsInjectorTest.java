// Copyright (c) 2003-present, Jodd Team (http://jodd.org)
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice,
// this list of conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright
// notice, this list of conditions and the following disclaimer in the
// documentation and/or other materials provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
// LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
// CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
// SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
// CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
// ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
// POSSIBILITY OF SUCH DAMAGE.

package jodd.madvoc.injector;

import jodd.madvoc.WebApp;
import jodd.madvoc.component.MadvocConfig;
import jodd.petite.PetiteContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MadvocParamsInjectorTest {

	@Test
	void testInjection() {
		WebApp webapp = new WebApp();
		webapp.start();
		PetiteContainer madpc = webapp.madvocContainer().petite();

		MadvocConfig madvocConfig = new MadvocConfig();

		String baseName = FooBean.class.getName();

		madpc.defineParameter("foo", "1");

		madpc.defineParameter(baseName + ".integer", "173");
		madpc.defineParameter(baseName + ".string", "jodd");
		madpc.defineParameter(baseName, "huh");

		MadvocParamsInjector madvocParamsInjector = new MadvocParamsInjector(madvocConfig);

		FooBean fooBean = new FooBean();

		madvocParamsInjector.injectContext(new Target(fooBean), null, madpc);

		assertEquals(173, fooBean.getInteger().intValue());
		assertEquals("jodd", fooBean.getString());
	}
}
