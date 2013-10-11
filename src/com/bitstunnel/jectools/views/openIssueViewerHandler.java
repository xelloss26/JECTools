/**
 * 
 */
package com.bitstunnel.jectools.views;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import com.bitstunnel.jectools.Control.ModelControl;
import com.bitstunnel.jectools.activator.Activator;

/**
 * @author Mai
 *
 */
public class openIssueViewerHandler implements IHandler {

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#addHandlerListener(org.eclipse.core.commands.IHandlerListener)
	 */
	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#dispose()
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub

//		 ILog log = Activator.getDefault().getLog();
//	     log.log(new Status(IStatus.OK, Activator.PLUGIN_ID,0,"openIssueViewerHandler was be exectue",null));
//	     
	     
		ModelControl controlProvider = new ModelControl();
		controlProvider.DBdataprcessor();
		IViewPart viewerPart = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage()
				.findView("com.bitstunnel.jectools.views.IssuesViewer");
		if (viewerPart == null)
			{
			System.out.println("viewerPart is null, Can't get right Viewer");
			return null;
			}
		
		if (viewerPart instanceof IssuesViewer)
		{
			final IssuesViewer issviewer = (IssuesViewer) viewerPart;
			issviewer.setModelControl(controlProvider);
			issviewer.initHeaders(controlProvider);
			issviewer.fillContents(controlProvider);
		}
		
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#isHandled()
	 */
	@Override
	public boolean isHandled() {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#removeHandlerListener(org.eclipse.core.commands.IHandlerListener)
	 */
	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

}
