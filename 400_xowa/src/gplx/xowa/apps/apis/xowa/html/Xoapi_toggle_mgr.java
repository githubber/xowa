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
package gplx.xowa.apps.apis.xowa.html; import gplx.*; import gplx.xowa.*; import gplx.xowa.apps.*; import gplx.xowa.apps.apis.*; import gplx.xowa.apps.apis.xowa.*;
import gplx.xowa.apps.cfgs.*;
public class Xoapi_toggle_mgr implements Gfo_invk {
	private Xoae_app app;
	private final    Ordered_hash hash = Ordered_hash_.New_bry();
	public void Ctor_by_app(Xoae_app app) {this.app = app;}
	public void Init_by_app(Xoae_app app) {
		Io_url img_dir = app.Fsys_mgr().Bin_xowa_file_dir().GenSubDir_nest("app.general");
		int len = hash.Count();
		for (int i = 0; i < len; ++i) {
			Xoapi_toggle_itm itm = (Xoapi_toggle_itm)hash.Get_at(i);
			itm.Init_fsys(img_dir);
		}

		app.Cfg().Bind_many_app(this
		, Cfg__toggles__offline_wikis
		, Cfg__toggles__wikidata_langs
		, Cfg__toggles__claim
		, Cfg__toggles__slink_w
		, Cfg__toggles__slink_d
		, Cfg__toggles__slink_s
		, Cfg__toggles__slink_v
		, Cfg__toggles__slink_q
		, Cfg__toggles__slink_b
		, Cfg__toggles__slink_u
		, Cfg__toggles__slink_n
		, Cfg__toggles__slink_special
		, Cfg__toggles__label
		, Cfg__toggles__descr
		, Cfg__toggles__alias
		, Cfg__toggles__json
		);
	}
	public Xoapi_toggle_itm Get_or_new(String key_str) {
		byte[] key_bry = Bry_.new_u8(key_str);
		Xoapi_toggle_itm rv = (Xoapi_toggle_itm)hash.Get_by(key_bry);
		if (rv == null) {
			rv = new Xoapi_toggle_itm(app, key_bry);
			hash.Add(key_bry, rv);
		}
		return rv;
	}
	public Object Invk(GfsCtx ctx, int ikey, String k, GfoMsg m) {
		if (ctx.MatchIn(k
		, Cfg__toggles__offline_wikis
		, Cfg__toggles__wikidata_langs
		, Cfg__toggles__claim
		, Cfg__toggles__slink_w
		, Cfg__toggles__slink_d
		, Cfg__toggles__slink_s
		, Cfg__toggles__slink_v
		, Cfg__toggles__slink_q
		, Cfg__toggles__slink_b
		, Cfg__toggles__slink_u
		, Cfg__toggles__slink_n
		, Cfg__toggles__slink_special
		, Cfg__toggles__label
		, Cfg__toggles__descr
		, Cfg__toggles__alias
		, Cfg__toggles__json)) {
			this.Get_or_new(String_.Replace(k, "xowa.app.recent.toggles.", "")).Visible_(m.ReadYn("v"));
		}
		else	return Gfo_invk_.Rv_unhandled;
		return this;
	}
	private static final String 
	  Cfg__toggles__offline_wikis		= "xowa.app.recent.toggles.offline-wikis"
	, Cfg__toggles__wikidata_langs		= "xowa.app.recent.toggles.wikidata-langs"
	, Cfg__toggles__claim				= "xowa.app.recent.toggles.wikidatawiki-claim"
	, Cfg__toggles__slink_w				= "xowa.app.recent.toggles.wikidatawiki-slink-wikipedia"
	, Cfg__toggles__slink_d				= "xowa.app.recent.toggles.wikidatawiki-slink-wiktionary"
	, Cfg__toggles__slink_s				= "xowa.app.recent.toggles.wikidatawiki-slink-wikisource"
	, Cfg__toggles__slink_v				= "xowa.app.recent.toggles.wikidatawiki-slink-wikivoyage"
	, Cfg__toggles__slink_q				= "xowa.app.recent.toggles.wikidatawiki-slink-wikiquote"
	, Cfg__toggles__slink_b				= "xowa.app.recent.toggles.wikidatawiki-slink-wikibooks"
	, Cfg__toggles__slink_u				= "xowa.app.recent.toggles.wikidatawiki-slink-wikiversity"
	, Cfg__toggles__slink_n				= "xowa.app.recent.toggles.wikidatawiki-slink-wikinews"
	, Cfg__toggles__slink_special		= "xowa.app.recent.toggles.wikidatawiki-slink-special"
	, Cfg__toggles__label				= "xowa.app.recent.toggles.wikidatawiki-label"
	, Cfg__toggles__descr				= "xowa.app.recent.toggles.wikidatawiki-descr"
	, Cfg__toggles__alias				= "xowa.app.recent.toggles.wikidatawiki-alias"
	, Cfg__toggles__json				= "xowa.app.recent.toggles.wikidatawiki-json"
	;
}
