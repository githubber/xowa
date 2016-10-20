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
package gplx.xowa.addons.bldrs.centrals.tasks; import gplx.*; import gplx.xowa.*; import gplx.xowa.addons.*; import gplx.xowa.addons.bldrs.*; import gplx.xowa.addons.bldrs.centrals.*;
import gplx.core.gfobjs.*;
public abstract class Xobc_task_regy__base {
	private final    Ordered_hash hash = Ordered_hash_.New();
	protected final    Xobc_task_mgr task_mgr;
	public Xobc_task_regy__base(Xobc_task_mgr task_mgr, String name) {this.task_mgr = task_mgr; this.name = name;}
	public String					Name()					{return name;} private final    String name;
	public int						Len()					{return hash.Len();}
	public void						Add(Xobc_task_itm t)	{hash.Add(t.Task_id(), t);}
	public void						Clear()					{hash.Clear();}
	public Xobc_task_itm			Get_at(int i)			{return (Xobc_task_itm)hash.Get_at(i);}
	public Xobc_task_itm			Get_by(int i)			{return (Xobc_task_itm)hash.Get_by(i);}
	public void						Del_by(int i)			{hash.Del(i);}
	public void						Sort()					{hash.Sort();}

	public void Save_to(Gfobj_ary ary, String lang_key) {
		List_adp list = List_adp_.New();
		int len = hash.Len();
		for (int i = 0; i < len; ++i) {
			Xobc_task_itm sub_task = (Xobc_task_itm)hash.Get_at(i);
			if (!String_.Eq(lang_key, Xobc_task_mgr.Lang_key__all)) {
				String task_key = sub_task.Task_key();
				if (!String_.Has_at_bgn(task_key, lang_key + ".")) continue;
			}
			list.Add(sub_task);
		}

		len = list.Len();
		Gfobj_nde[] sub_ndes = new Gfobj_nde[len];
		for (int i = 0; i < len; ++i) {
			Gfobj_nde sub_nde = sub_ndes[i] = Gfobj_nde.New();
			Xobc_task_itm sub_task = (Xobc_task_itm)list.Get_at(i);
			sub_task.Save_to(sub_nde);
		}
		ary.Ary_(sub_ndes);
	}
}
