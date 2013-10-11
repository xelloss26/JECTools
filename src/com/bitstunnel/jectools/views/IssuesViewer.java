package com.bitstunnel.jectools.views;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;










import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;

import com.bitstunnel.jectools.Control.ModelControl;
import com.bitstunnel.jectools.models.IssueVO;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class IssuesViewer extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bitstunnel.jectools.views.IssuesViewer";

	private TableViewer viewer;
	private Table table = null;
	private Action action1;
	private Action action2;
	private Action doubleClickAction;
	ModelControl con = null;

	/*
	 * The content provider class is responsible for
	 * providing objects to the view. It can wrap
	 * existing objects in adapters or simply return
	 * objects as-is. These objects may be sensitive
	 * to the current input of the view, or ignore
	 * it and always show the same content 
	 * (like Task List, for example).
	 */
	 
	class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}
		public void dispose() {
		}
		public Object[] getElements(Object parent) {
			
			return new String[] { "One", "Two", "Three" };
		}
	}
	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
//		private ArrayList<String> ColLabels = null;
		
		public ViewLabelProvider(ArrayList<String> ColTitles) {
//		ColLabels = ColTitles;
		setTableHeader(ColTitles);
		}
		
		public String getColumnText(Object obj, int index) {
			System.out.println("ViewLabelProvider getColumText was be called in " + index);
//			if (obj instanceof IssueVO)
//			{
//				if (index == 0)  return ((IssueVO)obj).getIssueID();
//				if (index == 1)  return ((IssueVO)obj).getPkey(); 
//				if (index == 2) return ((IssueVO)obj).getIssuestatus();
//				if (index == 3) return ((IssueVO)obj).getIssutype();
//				if (index == 4) return ((IssueVO)obj).getCreateDate().toString();
//				if (index == 5) return ((IssueVO)obj).getReporter();
//				if (index == 6) return ((IssueVO)obj).getSummary();
////				return (String)((ArrayList<String>) obj).get(index);
//			}
			if(obj instanceof IssueVO){
				try {
					return con.extractIssueVOContent((IssueVO)obj, index);
				} catch (ClassNotFoundException | NoSuchMethodException
						| SecurityException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			return null;


		}
		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}
		public Image getImage(Object obj) {

			return null;
//			return PlatformUI.getWorkbench().
//					getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
		
		private void setTableHeader(ArrayList<String> cols ) {
			if (cols.size()> 0) {
				if (table == null) {
				table = viewer.getTable();
				
				table.clearAll();
				for (String s : cols) {
					TableViewerColumn tvc = new TableViewerColumn(viewer, SWT.LEFT);
					tvc.getColumn().setText(s);		
					tvc.getColumn().setWidth(100);
				
					tvc.getColumn().addSelectionListener(getSelectionAdapter(table));
			
					table.setSortColumn(tvc.getColumn());
				}
				
    			table.setHeaderVisible(true);
				table.setLinesVisible(true);
				table.setSortColumn(table.getColumn(0));
				}
			}
		}
		
		private void setActiveSortColumn(Table cTable, SelectionEvent e){
			System.out.println("setActiveSortColumn was be call");
			TableColumn BeforeSortColumn = cTable.getSortColumn();
			TableColumn CurrentSortColumn = (TableColumn) e.widget;
			int dir = cTable.getSortDirection();
			if (BeforeSortColumn.equals(CurrentSortColumn))
			{
				dir  = dir == SWT.UP?SWT.DOWN:SWT.UP;
				cTable.setSortDirection(dir);
				
			} else 
			{
				cTable.setSortColumn(CurrentSortColumn);
			}
			
		}
		
		private SelectionAdapter getSelectionAdapter(final Table table){
			
			SelectionAdapter newAdapter = new SelectionAdapter(){
				@Override
				public void widgetSelected(SelectionEvent e)
				{
					System.out.println("widgetSelected was called " + e.widget.toString());
					setActiveSortColumn(table,e);
				}
			};
			return newAdapter;
			
			
		}
		
		
		
	}

	
	class IssuesViewerComparator extends ViewerComparator{
		@Override
		public int compare(Viewer viewer, Object e1, Object e2){
			IssueVO iv1 = (IssueVO)e1;
			IssueVO iv2 = (IssueVO)e2;
			return iv1.getPkey().compareTo(iv2.getPkey());
			
		}
		
	}

	/**
	 * The constructor.
	 */
	public IssuesViewer() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		

		System.out.println("createPartControl method was be called");
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
//		viewer.setContentProvider(new ArrayContentProvider());
//		viewer.setContentProvider(new ViewContentProvider());		
//	    viewer.setLabelProvider(new ViewLabelProvider((ArrayList<String>)modelcontrol.getIssueLabelsList()));
//		viewer.setSorter(new NameSorter());
//		viewer.setInput(getViewSite());
//		viewer.setInput(modelcontrol.getContentArray());

		// Create the help context id for the viewer's control
//		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "JECTools.viewer");
//		makeActions();
//		hookContextMenu();
//		hookDoubleClickAction();
//		contributeToActionBars();
	}
	
	@SuppressWarnings("unchecked")
	public void initHeaders(ModelControl controlProvider){
	
		viewer.setLabelProvider(new ViewLabelProvider((ArrayList<String>)controlProvider.getIssueLabelsList()));
	}
	
	public void fillContents(ModelControl controlProvider){
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setInput(controlProvider.getIssuesContentList());
		
	}
	public void setModelControl(ModelControl c){
		con  = c;
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				IssuesViewer.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(action1);
		manager.add(new Separator());
		manager.add(action2);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(action1);
		manager.add(action2);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(action1);
		manager.add(action2);
	}

	private void makeActions() {
		action1 = new Action() {
			public void run() {
				showMessage("Action 1 executed");
			}
		};
		action1.setText("Action 1");
		action1.setToolTipText("Action 1 tooltip");
		action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		action2 = new Action() {
			public void run() {
				showMessage("Action 2 executed");
			}
		};
		action2.setText("Action 2");
		action2.setToolTipText("Action 2 tooltip");
		action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				showMessage("Double-click detected on "+obj.toString());
			}
		};
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}
	private void showMessage(String message) {
		MessageDialog.openInformation(
			viewer.getControl().getShell(),
			"IssuesViewer",
			message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}