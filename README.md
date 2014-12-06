recyclerview
============

符合material design的recyclerview，为recyclerview添加了可加入headerview和footerview的方法，
添加方法：
addHeaderView(View mView)
addFooterView(View mView)
但是添加以后需要启用一下才可以显示：
setHaveHeaderView(boolean isHaveHeaderView)
setHaveFooterView(boolean isHaveFooterView)
删除方法：
removeHeaderView()
removeFooterView()

并且每一个item都可以有点击事件，类似listview. setOnItemClickListener事件

