/*
XOWA: the XOWA Offline Wiki Application
Copyright (C) 2012 gnosygnu@gmail.com

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package gplx.xowa.parsers.mws.prepros; import gplx.*; import gplx.xowa.*; import gplx.xowa.parsers.*; import gplx.xowa.parsers.mws.*;
import org.junit.*;
public class Xomw_prepro_wkr__tst {
	private final    Xomw_prepro_wkr__fxt fxt = new Xomw_prepro_wkr__fxt();
	@Test  public void Text() {
		fxt.Test__parse("abc", "<root>abc</root>");
	}
	@Test  public void Brack() {
		fxt.Test__parse("a[[b]]c", "<root>a[[b]]c</root>");
	}
	@Test  public void Template() {
		fxt.Test__parse("a{{b}}c", "<root>a<template lineStart=\"1\"><title>b</title></template>c</root>");
	}
	@Test  public void Tplarg() {
		fxt.Test__parse("a{{{b}}}c", "<root>a<tplarg lineStart=\"1\"><title>b</title></tplarg>c</root>");
	}
	@Test  public void Comment() {
		fxt.Test__parse("a<!--b-->c", "<root>a<comment>&lt;!--b--&gt;</comment>c</root>");
	}
	@Test  public void Comment__dangling() {
		fxt.Test__parse("a<!--b", "<root>a<comment>&lt;!--b</comment></root>");
	}
	@Test  public void Comment__ws() {		// NOTE: space is outside comment
		fxt.Test__parse("a <!--b--> c", "<root>a <comment>&lt;!--b--&gt;</comment> c</root>");
	}
	@Test  public void Comment__many__ws() {		// NOTE: space is outside comment
		fxt.Test__parse("a <!--1--> <!--2--> z", "<root>a <comment>&lt;!--1--&gt;</comment> <comment>&lt;!--2--&gt;</comment> z</root>");
	}
	@Test  public void Comment__nl__ws() {	// NOTE: space is inside comment if flanked by nl
		fxt.Test__parse(String_.Concat_lines_nl_skip_last
		( "a"
		, " <!--1--> "
		, " <!--2--> "
		, "z"
		), String_.Concat_lines_nl_skip_last
		( "<root>a"
		, "<comment> &lt;!--1--&gt; "
		, "</comment><comment> &lt;!--2--&gt; "
		, "</comment>z</root>"
		));
	}
	@Test  public void Ext__pre() {
		fxt.Test__parse("a<pre id=\"1\">b</pre>c", "<root>a<ext><name>pre</name><attr> id=&quot;1&quot;</attr><inner>b</inner><close>&lt;/pre&gt;</close></ext>c</root>");
	}
	@Test  public void Heading() {
		fxt.Test__parse(String_.Concat_lines_nl_skip_last
		( "a"
		, "== b1 =="
		, "z"
		), String_.Concat_lines_nl_skip_last
		( "<root>a"
		, "<h level=\"2\" i=\"1\">== b1 ==</h>"
		, "z</root>"
		));
	}
	@Test  public void Heading__eos__no_nl() {
		fxt.Test__parse(String_.Concat_lines_nl_skip_last
		( "a"
		, "== b1 =="
		), String_.Concat_lines_nl_skip_last
		( "<root>a"
		, "<h level=\"2\" i=\"1\">== b1 ==</h></root>"
		));
	}
	@Test  public void Heading__bos__implied_nl() {
		fxt.Test__parse(String_.Concat_lines_nl_skip_last
		( "== b1 =="
		, "z"
		), String_.Concat_lines_nl_skip_last
		( "<root><h level=\"2\" i=\"1\">== b1 ==</h>"
		, "z</root>"
		));
	}
	@Test  public void Inclusion__n() {
		fxt.Init__for_inclusion_(Bool_.N);
		fxt.Test__parse("a<onlyinclude>b</onlyinclude>c", "<root>a<ignore>&lt;onlyinclude&gt;</ignore>b<ignore>&lt;/onlyinclude&gt;</ignore>c</root>");
	}
	@Test  public void Inclusion__y() {
		fxt.Init__for_inclusion_(Bool_.Y);
		fxt.Test__parse("a<onlyinclude>b</onlyinclude>c", "<root><ignore>a&lt;onlyinclude&gt;</ignore>b<ignore>&lt;/onlyinclude&gt;c</ignore></root>");
	}
}
class Xomw_prepro_wkr__fxt {
	private final    Xomw_prepro_wkr wkr = new Xomw_prepro_wkr();
	private boolean for_inclusion = false;
	public Xomw_prepro_wkr__fxt() {
		wkr.Init_by_wiki("pre");
	}
	public void Init__for_inclusion_(boolean v) {for_inclusion = v;}
	public void Test__parse(String src_str, String expd) {
		byte[] src_bry = Bry_.new_u8(src_str);
		byte[] actl = wkr.Preprocess_to_xml(src_bry, for_inclusion);
		Tfds.Eq_str_lines(expd, String_.new_u8(actl), src_str);
	}
}
